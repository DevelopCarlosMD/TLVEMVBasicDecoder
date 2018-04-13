package com.coyotestudio.parserdecodetlvfromemv.kotlinversion

import com.coyotestudio.parserdecodetlvfromemv.kotlinversion.TagsEMV.Companion.UNKNOWN
import java.util.HashMap

/**
 * Created by J. Carlos Medina Díaz @_CarlosMD on 4/12/18.
 * carlos.medj@gmail.com
 */
class TagsEMV {

    private val tagsDictionary = HashMap<String, String>()

    fun initElements() {
        iniTagInfo()
    }


    private fun iniTagInfo() {

        tagsDictionary.put("9F01" , "Acquirer Identifier")
        tagsDictionary.put("9F40" , "Additional Terminal Capabilities")
        tagsDictionary.put("81" , "Amount, Authorised (Binary)")
        tagsDictionary.put("9F02" , "Amount, Authorised (Numeric)")
        tagsDictionary.put("9F04" , "Amount, Other (Binary)")
        tagsDictionary.put("9F03" , "Amount, Other (Numeric)")
        tagsDictionary.put("9F3A" , "Amount, Reference Currency")
        tagsDictionary.put("9F26" , "Application Cryptogram")
        tagsDictionary.put("9F42" , "Application Currency Code")
        tagsDictionary.put("9F44" , "Application Currency Exponent")
        tagsDictionary.put("9F05" , "Application Discretionary Data")
        tagsDictionary.put("5F25" , "Application Effective Date")
        tagsDictionary.put("5F24" , "Application Expiration Date")
        tagsDictionary.put("94" , "Application File Locator (AFL)")
        tagsDictionary.put("4F" , "Application Identifier (AID) – card")
        tagsDictionary.put("9F06" , "Application Identifier (AID) – terminal")
        tagsDictionary.put("82" , "Application Interchange Profile")
        tagsDictionary.put("50" , "Application Label")
        tagsDictionary.put("9F12" , "Application Preferred Name")
        tagsDictionary.put("5A" , "Application Primary Account Number (PAN)")
        tagsDictionary.put("5F34" , "Application Primary Account Number (PAN) Sequence Number")
        tagsDictionary.put("87" , "Application Priority Indicator")
        tagsDictionary.put("9F3B" , "Application Reference Currency")
        tagsDictionary.put("9F43" , "Application Reference Currency Exponent")
        tagsDictionary.put("61" , "Application Template")
        tagsDictionary.put("9F36" , "Application Transaction Counter (ATC)")
        tagsDictionary.put("9F07" , "Application Usage Control")
        tagsDictionary.put("9F08" , "Application Version Number")
        tagsDictionary.put("9F09" , "Application Version Number")
        tagsDictionary.put("89" , "Authorisation Code")
        tagsDictionary.put("8A" , "Authorisation Response Code")
        tagsDictionary.put("5F54" , "Bank Identifier Code (BIC)")
        tagsDictionary.put("8C" , "Card Risk Management Data Object List 1 (CDOL1)")
        tagsDictionary.put("8D" , "Card Risk Management Data Object List 2 (CDOL2)")
        tagsDictionary.put("5F20" , "Cardholder Name	")
        tagsDictionary.put("9F0B" , "Cardholder Name Extended")
        tagsDictionary.put("8E" , "Cardholder Verification Method (CVM) List")
        tagsDictionary.put("9F34" , "Cardholder Verification Method (CVM) Results")
        tagsDictionary.put("8F" , "Certification Authority Public Key Index")
        tagsDictionary.put("9F22" , "Certification Authority Public Key Index")
        tagsDictionary.put("83" , "Command Template")
        tagsDictionary.put("9F27" , "Cryptogram Information Data")
        tagsDictionary.put("9F45" , "Data Authentication Code")
        tagsDictionary.put("84" , "Dedicated File (DF) Name")
        tagsDictionary.put("9D" , "Directory Definition File (DDF) Name")
        tagsDictionary.put("73" , "Directory Discretionary Template")
        tagsDictionary.put("9F49" , "Dynamic Data Authentication Data Object List (DDOL)")
        tagsDictionary.put("70" , "EMV Proprietary Template")
        tagsDictionary.put("BF0C" , "File Control Information (FCI) Issuer Discretionary Data")
        tagsDictionary.put("A5" , "File Control Information (FCI) Proprietary Template")
        tagsDictionary.put("6F" , "File Control Information (FCI) Template")
        tagsDictionary.put("9F4C" , "ICC Dynamic Number")
        tagsDictionary.put("9F2D" , "Integrated Circuit Card (ICC) PIN Encipherment Public Key Certificate")
        tagsDictionary.put("9F2E" , "Integrated Circuit Card (ICC) PIN Encipherment Public Key Exponent")
        tagsDictionary.put("9F2F" , "Integrated Circuit Card (ICC) PIN Encipherment Public Key Remainder")
        tagsDictionary.put("9F46" , "Integrated Circuit Card (ICC) Public Key Certificate")
        tagsDictionary.put("9F47" , "Integrated Circuit Card (ICC) Public Key Exponent")
        tagsDictionary.put("9F48" , "Integrated Circuit Card (ICC) Public Key Remainder")
        tagsDictionary.put("9F1E" , "Interface Device (IFD) Serial Number")
        tagsDictionary.put("5F53" , "International Bank Account Number (IBAN)")
        tagsDictionary.put("9F0D" , "Issuer Action Code – Default")
        tagsDictionary.put("9F0E" , "Issuer Action Code – Denial")
        tagsDictionary.put("9F0F" , "Issuer Action Code – Online")
        tagsDictionary.put("9F10" , "Issuer Application Data")
        tagsDictionary.put("91" , "Issuer Authentication Data")
        tagsDictionary.put("9F11" , "Issuer Code Table Index")
        tagsDictionary.put("5F28" , "Issuer Country Code")
        tagsDictionary.put("5F55" , "Issuer Country Code (alpha2 format)")
        tagsDictionary.put("5F56" , "Issuer Country Code (alpha3 format)")
        tagsDictionary.put("42" , "Issuer Identification Number (IIN)")
        tagsDictionary.put("90" , "Issuer Public Key Certificate")
        tagsDictionary.put("9F32" , "Issuer Public Key Exponent")
        tagsDictionary.put("92" , "Issuer Public Key Remainder")
        tagsDictionary.put("86" , "Issuer Script Command")
        tagsDictionary.put("9F18" , "Issuer Script Identifier")
        tagsDictionary.put("71" , "Issuer Script Template 1")
        tagsDictionary.put("72" , "Issuer Script Template 2")
        tagsDictionary.put("5F50" , "Issuer URL")
        tagsDictionary.put("5F2D" , "Language Preference	1–4")
        tagsDictionary.put("9F13" , "Last Online Application Transaction Counter (ATC)")
        tagsDictionary.put("9F4D" , "Log Entry")
        tagsDictionary.put("9F4F" , "Log Format")
        tagsDictionary.put("9F14" , "Lower Consecutive Offline")
        tagsDictionary.put("9F15" , "Merchant Category Code")
        tagsDictionary.put("9F16" , "Merchant Identifier")
        tagsDictionary.put("9F4E" , "Merchant Name and Location")
        tagsDictionary.put("9F17" , "Personal Identification Number (PIN)")
        tagsDictionary.put("9F39" , "Point-of-Service (POS) Entry Mode")
        tagsDictionary.put("9F38" , "Processing Options Data Object List (PDOL)")
        tagsDictionary.put("80" , "Response Message Template Format 1")
        tagsDictionary.put("77" , "Response Message Template Format 2")
        tagsDictionary.put("5F30" , "Service Code")
        tagsDictionary.put("88" , "Short File Identifier (SFI)")
        tagsDictionary.put("9F4B" , "Signed Dynamic Application Data")
        tagsDictionary.put("93" , "Signed Static Application Data")
        tagsDictionary.put("9F4A" , "Static Data Authentication Tag List")
        tagsDictionary.put("9F33" , "Terminal Capabilities")
        tagsDictionary.put("9F1A" , "Terminal Country Code")
        tagsDictionary.put("9F1B" , "Terminal Floor Limit")
        tagsDictionary.put("9F1C" , "Terminal Identification")
        tagsDictionary.put("9F1D" , "Terminal Risk Management Data")
        tagsDictionary.put("9F35" , "Terminal Type")
        tagsDictionary.put("95" , "Terminal Verification Results")
        tagsDictionary.put("9F1F" , "Track 1 Discretionary Data")
        tagsDictionary.put("9F20" , "Track 2 Discretionary Data")
        tagsDictionary.put("57" , "Track 2 Equivalent Data	Contains the data elements of track 2 according to ISO/IEC")
        tagsDictionary.put("98" , "Transaction Certificate (TC)")
        tagsDictionary.put("97" , "Transaction Certificate Data")
        tagsDictionary.put("5F2A" , "Transaction Currency Code")
        tagsDictionary.put("5F36" , "Transaction Currency Exponent")
        tagsDictionary.put("9A" , "Transaction Date")
        tagsDictionary.put("99" , "Transaction Personal Identification Number (PIN) Data")
        tagsDictionary.put("9F3C" , "Transaction Reference Currency Code")
        tagsDictionary.put("9F3D" , "Transaction Reference Currency Exponent")
        tagsDictionary.put("9F41" , "Transaction Sequence Counter")
        tagsDictionary.put("9B" , "Transaction Status Information")
        tagsDictionary.put("9F21" , "Transaction Time")
        tagsDictionary.put("9C" , "Transaction Type")
        tagsDictionary.put("9F37" , "Unpredictable Number")
        tagsDictionary.put("9F23" , "Upper Consecutive Offline Limit")
    }

    fun getTagInfo(tag: String): String {
        return if (tagsDictionary.get(tag) != null)
            tagsDictionary.get(tag)!!
        else
            UNKNOWN
    }

    companion object {
        const val UNKNOWN = "Unknown tag"
    }

}
