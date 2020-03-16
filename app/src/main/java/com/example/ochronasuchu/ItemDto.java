package com.example.ochronasuchu;

class ItemDto {

    public String getMf125() {
        return Mf125;
    }
    public String getMf250() {
        return Mf250;
    }
    public String getMf500() {
        return Mf500;
    }
    public String getMf1000() {
        return Mf1000;
    }
    public String getMf2000() {
        return Mf2000;
    }
    public String getMf4000() {
        return Mf4000;
    }
    public String getMf8000() {
        return Mf8000;
    }
    public String getSf125() {
        return sf125;
    }
    public String getSf250() {
        return sf250;
    }
    public String getSf500() {
        return sf500;
    }
    public String getSf1000() {
        return sf1000;
    }
    public String getSf2000() {
        return sf2000;
    }
    public String getSf4000() {
        return sf4000;
    }
    public String getSf8000() {
        return sf8000;
    }
    public String getAPV125() {
        return APV125;
    }
    public String getAPV250() {
        return APV250;
    }
    public String getAPV500() {
        return APV500;
    }
    public String getAPV1000() {
        return APV1000;
    }
    public String getAPV2000() {
        return APV2000;
    }
    public String getAPV4000() {
        return APV4000;
    }
    public String getAPV8000() {
        return APV8000;
    }
    public String getH() {
        return H;
    }
    public String getM() {
        return M;
    }
    public String getL() {
        return L;
    }
    public String getSNR() {
        return SNR;
    }
    public String getTyp() {
        return typ;
    }
    public String getProd() {
        return prod;
    }
    public String getModel() {
        return model;
    }
    public String getCerty() {
        return certy;
    }


    private String typ;
    private String prod;
    private String model;
    private String Mf125;
    private String Mf250;
    private String Mf500;
    private String Mf1000;
    private String Mf2000;
    private String Mf4000;
    private String Mf8000;
    private String sf125;
    private String sf250;
    private String sf500;
    private String sf1000;
    private String sf2000;
    private String sf4000;
    private String sf8000;
    private String APV125;
    private String APV250;
    private String APV500;
    private String APV1000;
    private String APV2000;
    private String APV4000;
    private String APV8000;
    private String H;
    private String M;
    private String L;
    private String SNR;
    private String certy;

    public ItemDto(String typ, String prod, String model, String Mf125, String Mf250, String Mf500, String Mf1000, String Mf2000, String Mf4000, String Mf8000,
                   String sf125, String sf250, String sf500, String sf1000, String sf2000, String sf4000, String sf8000,
                   String APV125, String APV250, String APV500, String APV1000, String APV2000, String APV4000, String APV8000,
                   String H, String M, String L, String SNR, String certy) {
        this.typ = typ;
        this.prod = prod;
        this.model = model;
        this.Mf125 = Mf125;
        this.Mf250 = Mf250;
        this.Mf500 = Mf500;
        this.Mf1000 = Mf1000;
        this.Mf2000 = Mf2000;
        this.Mf4000 = Mf4000;
        this.Mf8000 = Mf8000;
        this.sf125 = sf125;
        this.sf250 = sf250;
        this.sf500 = sf500;
        this.sf1000 = sf1000;
        this.sf2000 = sf2000;
        this.sf4000 = sf4000;
        this.sf8000 = sf8000;
        this.APV125 = APV125;
        this.APV250 = APV250;
        this.APV500 = APV500;
        this.APV1000 = APV1000;
        this.APV2000 = APV2000;
        this.APV4000 = APV4000;
        this.APV8000 = APV8000;
        this.H = H;
        this.M = H;
        this.L = H;
        this.SNR = SNR;
        this.certy = certy;
    }



}