package com.mabes.projectakhir.ui.addedit

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mabes.projectakhir.*
import com.mabes.projectakhir.data.remote.response.Data
import com.mabes.projectakhir.data.remote.response.DataRanks
import com.mabes.projectakhir.data.remote.response.DataStatus
import com.mabes.projectakhir.data.remote.response.SubmitData

import com.mabes.projectakhir.databinding.ActivityAddEditBinding
import com.mabes.projectakhir.ui.detail.DetailUserActivity
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddEditActivity : AppCompatActivity() {

    private lateinit var addEditBinding: ActivityAddEditBinding
    private val addEditViewModel: AddEditViewModel by viewModels()

    private val listIdStatus = ArrayList<Int>()
    private val listNameStatus = ArrayList<String>()

    private val listIdRanks = ArrayList<Int>()
    private val listNameRanks = ArrayList<String>()

    private var rankId = 0
    private var statusId = 0

    private var myFile: File? = null

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addEditBinding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(addEditBinding.root)

        supportActionBar?.hide()

        addEditViewModel.listStatus.observe(this) {
            showStatus(it)
        }

        addEditViewModel.listRanks.observe(this) {
            showRanks(it)
        }

        val dataDetail = intent.getParcelableExtra<Data>(DetailUserActivity.DETAIL_DATA_PREF)
        val isEdit = intent.getBooleanExtra(DetailUserActivity.DETAIL_DATA_ISEDIT, false)

        if (isEdit) {
            addEditBinding.tvAppBarAdd.setText("Edit User")
            addEditBinding.tvFoto.visibility = View.GONE
            addEditBinding.btnPickImage.visibility = View.GONE
            addEditBinding.tvFileFoto.visibility = View.GONE
        }

        addEditBinding.apply {
            edAlamat.setText(dataDetail?.address)
            edNRP.setText(dataDetail?.nrp)
            edNama.setText(dataDetail?.name)
            edTempatLahir.setText(dataDetail?.bornPlace)
            edTanggalLahir.setText(dataDetail?.bornDate)
            actPangkat.setText(dataDetail?.rank)
            actStatus.setText(dataDetail?.status)
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }

        addEditViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        addEditBinding.actStatus.setOnItemClickListener { adapterView, view, i, l ->
            statusId = (adapterView.getItemIdAtPosition(i) + 1).toInt()
            Toast.makeText(this, "${adapterView.getItemIdAtPosition(i) + 1}", Toast.LENGTH_SHORT)
                .show()
        }

        addEditBinding.actPangkat.setOnItemClickListener { adapterView, view, i, l ->
            rankId = (adapterView.getItemIdAtPosition(i) + 1).toInt()
            Toast.makeText(this, "${adapterView.getItemIdAtPosition(i) + 1}", Toast.LENGTH_SHORT)
                .show()
        }

        addEditBinding.btnSubmit.setOnClickListener {
            Toast.makeText(this, "Rank id : $rankId, Status: $statusId", Toast.LENGTH_SHORT).show()
            val submitData = SubmitData(
                name = addEditBinding.edNama.text.toString(),
                address = addEditBinding.edAlamat.text.toString(),
                bornDate = addEditBinding.edTanggalLahir.text.toString(),
                nrp = addEditBinding.edNRP.text.toString(),
                bornPlace = addEditBinding.edNRP.text.toString(),
                rankId = rankId,
                statusId = statusId,
                image = myFile
            )
            if (isEdit) {
                Toast.makeText(this, "EDIT", Toast.LENGTH_SHORT).show()
                val id = dataDetail?.id
                if (id != null) {
                    addEditViewModel.submitEditUser(id, submitData)
                }
            } else {
                Toast.makeText(this, "ADD NEW", Toast.LENGTH_SHORT).show()
                addEditViewModel.submitNewUser(submitData)
            }

            addEditViewModel.responseMessage.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
            finish()
        }

        addEditBinding.btnPickImage.setOnClickListener {
            pickImage()
        }
    }

    private fun showRanks(data: List<DataRanks>) {
        data.forEach {
            listIdRanks.add(it.id)
            listNameRanks.add(it.name)
        }

        for (rank in listNameRanks) {
            if (rank == addEditBinding.actPangkat.text.toString()) {
                rankId = listNameRanks.indexOf(rank) + 1
            }
        }

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, listNameRanks)
        val actRanks = addEditBinding.actPangkat

        actRanks.setAdapter(arrayAdapter)

    }

    private fun showStatus(data: List<DataStatus>) {
        data.forEach {
            listIdStatus.add(it.id)
            listNameStatus.add(it.name)
        }

        for (status in listNameStatus) {
            if (status == addEditBinding.actStatus.text.toString()) {
                statusId = listNameStatus.indexOf(status) + 1
            }
        }

        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, listNameStatus)
        val actStatus: AutoCompleteTextView = addEditBinding.actStatus

        actStatus.setAdapter(arrayAdapter)
    }

    private fun showLoading(isLoading: Boolean) {
        addEditBinding.loadRv.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImage: Uri = result.data?.data as Uri
            myFile = uriToFile(selectedImage, this@AddEditActivity)
            addEditBinding.tvFileFoto.text = myFile!!.name
        }
    }

    private val timestamp: String = SimpleDateFormat(
        "dd-MMM-yyyy",
        Locale.US
    ).format(System.currentTimeMillis())

    private fun uriToFile(selectedImage: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = File.createTempFile("pers-$timestamp", ".jpg", context.getExternalFilesDir(Environment.DIRECTORY_PICTURES))

        val inputStream = contentResolver.openInputStream(selectedImage) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private fun pickImage() {
        val pickIntent = Intent()
        pickIntent.type = "image/*"
        pickIntent.action = Intent.ACTION_GET_CONTENT

        launcherIntentGallery.launch(Intent.createChooser(pickIntent, "Pilih Foto"))
    }

}