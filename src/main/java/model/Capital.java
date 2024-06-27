package model;

public enum Capital {
    ARACAJU("-10.9472","-37.0731"),
    BELEM("-1.4558","-48.5039"),
    BELO_HORIZONTE("-19.8157","-43.9542"),
    BOA_VISTA("2.8235","-60.6758"),
    BRASILIA("-15.7801","-47.9292"),
    CAMPO_GRANDE("-20.4697","-54.6201"),
    CUIABA("-15.6010","-56.0974"),
    CURITIBA("-25.4296","-49.2713"),
    FLORIANOPOLIS("-27.5954","-48.5480"),
    FORTALEZA("-3.7172","-38.5434"),
    GOIANIA("-16.6869","-49.2648"),
    JOAO_PESSOA("-7.1153","-34.861"),
    MACAPA("0.0340","-51.0705"),
    MACEIO("-9.6658","-35.7350"),
    MANAUS("-3.1190","-60.0217"),
    NATAL("-5.7945", "-35.211"),
    PALMAS("-10.2128", "-48.3602"),
    PORTO_ALEGRE("-30.0346","-51.2177"),
    PORTO_VELHO("-8.7608", "-63.9014"),
    RECIFE("-8.0476", "-34.8770"),
    RIO_BRANCO("-9.9754","-67.8243"),
    RIO_DE_JANEIRO("-22.9068", "-43.1729"),
    SALVADOR("-12.9714", "-38.5014"),
    SAO_LUIS("-2.5387", "-44.2829"),
    SAO_PAULO("-23.5505", "-46.6333"),
    TERESINA("-5.0892", "-42.8016"),
    VITORIA("-20.3155", "-40.3128");

    private final String latitude;
    private final String longitude;

    Capital(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
