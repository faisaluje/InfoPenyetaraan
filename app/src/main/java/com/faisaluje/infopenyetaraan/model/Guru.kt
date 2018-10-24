package com.faisaluje.infopenyetaraan.model

import com.google.gson.annotations.SerializedName

data class Guru(
        @SerializedName("ptk_inpassing_id") val ptkInpassingId: String,
        @SerializedName("no_urut") val noUrut: Int,
        @SerializedName("periode_id") val periodeId: String,
        @SerializedName("no_berkas") val noBerkas: String,
        @SerializedName("nuptk") val nuptk: String,
        @SerializedName("nrg") val nrg: String,
        @SerializedName("nama") val nama: String,
        @SerializedName("jenis_kelamin") val jenisKelamin: String,
        @SerializedName("tempat_lahir") val tempatLahir: String,
        @SerializedName("tgl_lahir") val tglLahir: String,
        @SerializedName("email") val email: String,
        @SerializedName("ijazah_terakhir_id") val ijazahTerakhirId: Int,
        @SerializedName("nama_ijazah_terakhir") val namaIjazahTerakhir: String,
        @SerializedName("kode_bidang_studi_sertifikasi") val kodeBidangStudiSertifikasi: String,
        @SerializedName("nama_bidang_studi_sertifikasi") val namaBidangStudiSertifikasi: String,
        @SerializedName("mapel_diajarkan") val mapelDiajarkan: String,
        @SerializedName("status_kepegawaian_id") val statusKepegawaianId: Int,
        @SerializedName("nama_status_kepegawaian") val namaStatusKepegawaian: String,
        @SerializedName("jenis_guru") val jenisGuru: String,
        @SerializedName("tugas_tambahan_id") val tugasTambahanId: Int,
        @SerializedName("nama_tugas_tambahan") val namaTugasTambahan: String,
        @SerializedName("jjm") val jjm: Int,
        @SerializedName("masa_kerja_tahun") val masaKerjaTahun: Int,
        @SerializedName("masa_kerja_bulan") val masaKerjaBulan: Int,
        @SerializedName("tmt_gtt") val tmtGtt: String,
        @SerializedName("tmt_gty") val tmtGty: String,
        @SerializedName("sekolah_id") val sekolahId: String,
        @SerializedName("nama_sekolah") val namaSekolah: String,
        @SerializedName("npsn") val npsn: String,
        @SerializedName("bentuk_pendidikan_id") val bentukPendidikanId: Int,
        @SerializedName("nama_bentuk_pendidikan") val namaBentukPendidikan: String,
        @SerializedName("alamat_sekolah") val alamatSekolah: String,
        @SerializedName("kecamatan_id") val kecamatanId: Int,
        @SerializedName("nama_kecamatan") val namaKecamatan: String,
        @SerializedName("kabupaten_kota_id") val kabupatenKotaId: Int,
        @SerializedName("nama_kabupaten_kota") val namaKabupatenKota: String,
        @SerializedName("propinsi_id") val propinsiId: Int,
        @SerializedName("nama_propinsi") val namaPropinsi: String,
        @SerializedName("status_dokumen_id") val statusDokumenId: Int,
        @SerializedName("tgl_pengajuan") val tglPengajuan: String,
        @SerializedName("semester_id") val semesterId: Int,
        @SerializedName("jenjang_id") val jenjangId: Int,
        @SerializedName("processing") val processing: Int,
        @SerializedName("last_update") val lastUpdate: String,
        @SerializedName("created_by") val createdBy: String,
        @SerializedName("status_dokumen") val statusDokumen: String,
        @SerializedName("terima_berkas") val terimaBerkas: String,
        @SerializedName("dokumen") val dokumen: List<Dokumen>
)