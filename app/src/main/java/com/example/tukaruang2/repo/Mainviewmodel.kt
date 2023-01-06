package com.example.tukaruang2.repo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tukaruang2.model.APIdata
import com.example.tukaruang2.util.dispatcherprov
import com.example.tukaruang2.util.resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

//viewmodel data
class Mainviewmodel @ViewModelInject constructor( //dager hilt
    private val repository : Mainrepo,
    private val dispatcher : dispatcherprov
    ): ViewModel() {
        //class event
        sealed class currevent {
            class berhasil(val sukses: String): currevent() //berhasil
            class gagal(val fail: String): currevent() //gagal
            object loading: currevent() //loading
            object empty : currevent() //kosong
        }
    private val _konversi = MutableStateFlow<currevent>(currevent.empty)
    val konversion: StateFlow<currevent> = _konversi

    //konversi
    fun convert(jumlahStr : String,dari : String,ke : String){
        val darijumlah = jumlahStr.toFloatOrNull()
        if(darijumlah == null){ //cek input kosong atau tidak
            _konversi.value = currevent.gagal("error kosong")
            return
        }
        //kotlin corutines
        viewModelScope.launch(dispatcher.io) {
            _konversi.value = currevent.loading
            when(val konrespon = repository.getrates(dari)){ //konrespon = konversi respon
                is resources.error -> _konversi.value = currevent.gagal(konrespon.messeage!!)
                is resources.success ->{ //jika berhasil
                    val rates = konrespon.data!!.APIdata //pass api data
                    val rate = ambilcurr(ke,rates)
                    if(rate == null){
                        _konversi.value = currevent.gagal("gagal")
                    }else{
                        val convertedcurr = round(darijumlah * rate * 100)/100

                        _konversi.value = currevent.berhasil("$darijumlah $ke = $convertedcurr")
                    }
                }
            }
        }
    }
    //ambil rates
    private fun ambilcurr(currency: String, rate : APIdata) = when (currency){
        "AED" -> rate.uSDAED
        "AFN" -> rate.uSDAFN
        "ALL" -> rate.uSDALL
        "AMD" -> rate.uSDMAD
        "ANG" -> rate.uSDANG
        "AOA" -> rate.uSDAOA
        "ARS" -> rate.uSDARS
        "AUD" -> rate.uSDAUD
        "AWG" -> rate.uSDAWG
        "AZN" -> rate.uSDAZN
        "BAM" -> rate.uSDBAM
        "BBD" -> rate.uSDBBD
        "BDT" -> rate.uSDBDT
        "BGN" -> rate.uSDBGN
        "BHD" -> rate.uSDBHD
        "BIF" -> rate.uSDBIF
        "BMD" -> rate.uSDBMD
        "BND" -> rate.uSDBND
        "BOB" -> rate.uSDBOB
        "BRL" -> rate.uSDBRL
        "BSD" -> rate.uSDBSD
        "BTC" -> rate.uSDBTC
        "BTN" -> rate.uSDBTN
        "BWP" -> rate.uSDBWP

        "BYN" -> rate.uSDBYN
        "BYR" -> rate.uSDBYR
        "BZD" -> rate.uSDBZD
        "CAD" -> rate.uSDCAD
        "CDF" -> rate.uSDCDF
        "CHF" -> rate.uSDCHF
        "CLF" -> rate.uSDCLF
        "CLP" -> rate.uSDCLP
        "CNY" -> rate.uSDCNY
        "COP" -> rate.uSDCOP
        "CRC" -> rate.uSDCRC
        "CUC" -> rate.uSDCUC
        "CVE" -> rate.uSDCVE
        "CZK" -> rate.uSDCZK
        "DJF" -> rate.uSDDJF
        "DKK" -> rate.uSDDKK
        "DOP" -> rate.uSDDOP
        "DZD" -> rate.uSDDZD
        "EGP" -> rate.uSDEGP
        "ERN" -> rate.uSDERN
        "ETB" -> rate.uSDETB
        "EUR" -> rate.uSDEUR
        "FJD" -> rate.uSDFJD
        "FKP" -> rate.uSDFKP

        "GBP" -> rate.uSDGBP
        "GEL" -> rate.uSDGEL
        "GGP" -> rate.uSDGGP
        "GHS" -> rate.uSDGHS
        "GIP" -> rate.uSDGIP
        "GMD" -> rate.uSDCHF
        "GNF" -> rate.uSDGNF
        "GTQ" -> rate.uSDGTQ
        "GYD" -> rate.uSDGYD
        "HKD" -> rate.uSDHKD
        "HNL" -> rate.uSDHNL
        "HRK" -> rate.uSDHRK
        "HTG" -> rate.uSDHTG
        "HUF" -> rate.uSDHUF
        "IDR" -> rate.uSDIDR
        "ILS" -> rate.uSDILS
        "IMP" -> rate.uSDIMP
        "INR" -> rate.uSDINR
        "IQD" -> rate.uSDIQD
        "IRR" -> rate.uSDIRR
        "ISK" -> rate.uSDISK
        "JEP" -> rate.uSDJEP
        "JMD" -> rate.uSDJMD
        "JOD" -> rate.uSDJOD

        "JPY" -> rate.uSDJPY
        "KES" -> rate.uSDKES
        "KGS" -> rate.uSDKGS
        "KHR" -> rate.uSDKHR
        "KMF" -> rate.uSDKMF
        "KPW" -> rate.uSDKPW
        "KRW" -> rate.uSDKRW
        "KRW" -> rate.uSDKRW
        "KYD" -> rate.uSDKYD
        "KZT" -> rate.uSDKZT
        "LAK" -> rate.uSDLAK
        "LBP" -> rate.uSDLBP
        "LKR" -> rate.uSDLKR
        "LRD" -> rate.uSDLRD
        "LSL" -> rate.uSDLSL
        "LTL" -> rate.uSDLTL
        "LVL" -> rate.uSDLYD
        "MAD" -> rate.uSDMAD
        "MDL" -> rate.uSDMDL
        "MGA" -> rate.uSDMGA
        "MKD" -> rate.uSDMKD
        "MMK" -> rate.uSDMMK
        "MNT" -> rate.uSDMNT
        "MOP" -> rate.uSDMOP

        "MRO" -> rate.uSDJPY
        "MUR" -> rate.uSDMUR
        "MVR" -> rate.uSDMVR
        "MWK" -> rate.uSDMWK
        "MXN" -> rate.uSDMXN
        "MYR" -> rate.uSDMYR
        "MZN" -> rate.uSDMZN
        "NAD" -> rate.uSDNAD
        "NGN" -> rate.uSDNGN
        "NIO" -> rate.uSDNIO
        "NOK" -> rate.uSDNOK
        "NPR" -> rate.uSDNPR
        "NZD" -> rate.uSDNZD
        "OMR" -> rate.uSDOMR
        "PAB" -> rate.uSDPAB
        "PEN" -> rate.uSDPEN
        "PGK" -> rate.uSDPGK
        "PHP" -> rate.uSDPHP
        "PKR" -> rate.uSDPKR
        "PLn" -> rate.uSDPLN
        "PYG" -> rate.uSDPYG
        "QAR" -> rate.uSDQAR
        "RON" -> rate.uSDRON
        "RSD" -> rate.uSDRSD

        "RUB" -> rate.uSDRUB
        "RWF" -> rate.uSDRWF
        "SAR" -> rate.uSDSAR
        "SBD" -> rate.uSDSBD
        "SCR" -> rate.uSDSCR
        "SDG" -> rate.uSDSDG
        "SEK" -> rate.uSDSEK
        "SGD" -> rate.uSDSGD
        "SHP" -> rate.uSDSHP
        "SLL" -> rate.uSDSLL
        "SOS" -> rate.uSDSOS
        "SRD" -> rate.uSDSRD
        "STD" -> rate.uSDSTD
        "SVC" -> rate.uSDSVC
        "SYP" -> rate.uSDSYP
        "SZL" -> rate.uSDSZL
        "THB" -> rate.uSDTHB
        "TJS" -> rate.uSDTJS
        "TMT" -> rate.uSDTMT
        "TND" -> rate.uSDTND
        "TOP" -> rate.uSDTOP
        "TRY" -> rate.uSDTRY
        "TTD" -> rate.uSDTTD
        "TWD" -> rate.uSDTWD

        "TZS" -> rate.uSDTZS
        "UAH" -> rate.uSDUAH
        "UGX" -> rate.uSDUGX
        "USD" -> rate.uSDUSD
        "UYU" -> rate.uSDUYU
        "UZS" -> rate.uSDUZS
        "VEF" -> rate.uSDVEF
        "VND" -> rate.uSDVND
        "VUV" -> rate.uSDVUV
        "WST" -> rate.uSDWST
        "XAF" -> rate.uSDXAF
        "XAG" -> rate.uSDXAG
        "XAU" -> rate.uSDXAU
        "XCD" -> rate.uSDXCD
        "XDR" -> rate.uSDXDR
        "XOF" -> rate.uSDXOF
        "XPF" -> rate.uSDXPF
        "YER" -> rate.uSDYER
        "ZAR" -> rate.uSDZAR
        "ZMK" -> rate.uSDZMK
        "ZMW" -> rate.uSDZMW
        "ZWL" -> rate.uSDZWL
        else -> null
    }
}