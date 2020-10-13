package com.android.app.orquesta.Util;

public class Constants {

    public static final String SERVER_PATH = "https://orquestaymas.com/";

    //ORQUESTA
    public static final String AGRUPACION_LIST = SERVER_PATH + "json/getAgrupacion.php";
    public static final String AGRUPACION_EVENTO_DETALLE_LIST = SERVER_PATH + "json/getAgrupacionEventoDetalle.php?idAgrupacion=";
    public static final String AGRUPACION_PERSON_DETALLE_LIST = SERVER_PATH + "json/getAgrupacionPersonaDetalle.php?idAgrupacion=";
    public static final String AGRUPACION_VIDEOS_DETALLE_LIST = SERVER_PATH + "json/getAgrupacionVideosDetalle.php?idAgrupacion=";
    public static final String AGRUPACION_CONTACTAR_DETALLE_LIST = SERVER_PATH + "json/getAgrupacionContactarDetalle.php?idAgrupacion=";

    public static final String REGISTAR_COMENTARIO = SERVER_PATH + "json/guardarComentario.php";
    public static final String REGISTAR_CALIFICACION = SERVER_PATH + "json/guardarCalificacion.php";
    public static final String UPDATE_CALIFICACION = SERVER_PATH + "json/updateCalificacion.php";

    public static final String AGRUPACION_SEARCH = SERVER_PATH + "json/getSearchAgrupacion.php?Search=";
    public static final String LISTART_COMENTARIO = SERVER_PATH + "json/getComentario.php?idAgrupacion=";

    public static final String OBTENER_CALIFICACION = SERVER_PATH + "json/getCalificacion.php?idAgrupacion=";
    public static final String OBTENER_CALIFICACION_TOTAL = SERVER_PATH + "json/getCalificacionTotal.php?idAgrupacion=";

    public static final String AGRUPACION_IMAGEN_URL = SERVER_PATH + "agrupacion/fotos/";
    public static final String PERSONA_IMAGEN_URL = SERVER_PATH + "persona/fotos/";


    public static final String CONTRATO_LIST = SERVER_PATH + "json/getContrato.php";
    public static final String CONYTRATO_SEARCH = SERVER_PATH + "json/getContrato.php?Search=";

    public static final String EVENTO_LIST = SERVER_PATH + "json/getEvento.php";
    public static final String EVENTO_SEARCH = SERVER_PATH + "json/getEvento.php?Search=";

    public static final String REGISTAR_EVENTO = SERVER_PATH + "json/guardarEvento.php";
    public static final String REGISTAR_PERSONA = SERVER_PATH + "json/guardarPersona.php";

    public static final String UBIGEO_DEPARTAMENTO = SERVER_PATH + "json/getDepartamento.php";
    public static final String UBIGEO_PROVINCIA = SERVER_PATH + "json/getProvincia.php?idDepatamento=";
    public static final String UBIGEO_DISTRITO = SERVER_PATH + "json/getDistrito.php?";

    public static final String ENUMERADO_TIPO = SERVER_PATH + "json/getEnumerado.php?tipoEnumerado=";

    public static final String LISTAR_TIPO_ENUMERADO = SERVER_PATH + "json/getEnumerado.php?tipoEnumerado=";



    public static final String AGRUPACION_DETAIL_INTENT = "agrupacionDetailIntentOrquesta";


    //end orquesta




    /*LOGIN*/
    public static final String USER_LOGIN_URL = SERVER_PATH + "json/login.php";
    /*END*/

    public static final String LOGIN_PREV_ACTIVITY = "loginPrevActivityBootic";
    public static final String LOGIN_PREV_MAIN_ACTIVITY = "loginPrevMainActivityBootic";


    public static final String SHARED_PREF_FAVOURITE = "sharedPreferencesFavouritesBootic";

    public static final String SHARED_PREF_USER = "sharedPreferencesUserBootic";
    public static final String SHARED_PREF_LOGGEDIN_USER = "sharedPreferencesLoggedInUserBootic";

    public static final String SHARED_PREF_CURRENCY = "sharedPreferencesCurrencyBootic";
    public static final String SHARED_PREF_CURRENCY_IN = "sharedPreferencesCurrencyInBootic";

    public static final String SHARED_PREF_EMPTY_CART = "sharedPreferencesEmptyCartBootic";
    public static final String SHARED_PREF_EMPTY_CART_IN = "sharedPreferencesEmptyCartInBootic";

    public static final String SHARED_PREF_FCMTOKEN = "sharedPreferencesFCMTokenBootic";
    public static final String SHARED_PREF_FCMTOKEN_IN = "sharedPreferencesFCMTokenInBootic";

    public static final String SHARED_PREF_PROFILEIMAGE = "sharedPreferencesProfileImageBootic";
    public static final String SHARED_PREF_PROFILEIMAGE_IN = "sharedPreferencesProfileImageInBootic";



}
