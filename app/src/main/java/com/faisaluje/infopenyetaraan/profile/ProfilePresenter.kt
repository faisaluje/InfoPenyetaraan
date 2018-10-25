package com.faisaluje.infopenyetaraan.profile

import com.faisaluje.infopenyetaraan.model.Guru
import com.faisaluje.infopenyetaraan.model.Profile

class ProfilePresenter(private val view: ProfileView,
                       private val guru: Guru){
    private val profile: MutableList<Profile> = mutableListOf()

    fun getProfile(){
        view.showLoading()

        profile.add(Profile(1,"No. Berkas",guru.noBerkas))
        profile.add(Profile(2,"NUPTK",guru.nuptk))
        profile.add(Profile(3,"NRG",guru.nrg))
        profile.add(Profile(4,"Nama Guru",guru.nama))
        profile.add(Profile(5,"Ijazah Terakhir",guru.namaIjazahTerakhir))
        profile.add(Profile(6,"Mapel Sertifikasi",guru.namaBidangStudiSertifikasi))
        profile.add(Profile(7,"Mapel Diajarkan",guru.mapelDiajarkan))
        profile.add(Profile(8,"Jenis Guru",guru.jenisGuru))
        profile.add(Profile(9,"Nama Sekolah",guru.namaSekolah))
        profile.add(Profile(10,"NPSN",guru.npsn))
        profile.add(Profile(11,"Kabupaten/Kota",guru.namaKabupatenKota))
        profile.add(Profile(12,"Provinsi",guru.namaPropinsi))
        profile.add(Profile(13,"Tgl Berkas Diterima",guru.terimaBerkas))
        profile.add(Profile(14,"Status Dokumen",guru.statusDokumen))
        profile.add(Profile(15,"Tgl Update Terakhir",guru.lastUpdate))

        view.showListProfile(profile)
        view.hideLoading()
    }
}