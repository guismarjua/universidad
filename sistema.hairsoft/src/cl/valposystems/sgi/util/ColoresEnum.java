package cl.valposystems.sgi.util;

public enum ColoresEnum {

	AMARILLO (String.valueOf("amarillo"), "FFED61"),
    AZUL (String.valueOf("azul"), "002E66"),
    BLANCO (String.valueOf("blanco"), "FFFFFF"),
    GRIS (String.valueOf("gris"), "808080"),
    CAFE (String.valueOf("cafe"), "8C4E00"),
	CELESTE (String.valueOf("celeste"), "366DB2"),
	CIAN (String.valueOf("cian"), "0EAAB2"),
	PM (String.valueOf("pm"), "de0707"),
    NARANJA (String.valueOf("naranja"), "f9964e"),
    ROJO (String.valueOf("rojo"), "de0707"),
    VERDE (String.valueOf("verde"), "52be80"),
    PLANIFICADO (String.valueOf("Planificado"), "366DB2"),
    NOK (String.valueOf("NOK"), "de0707"),
    OK (String.valueOf("OK"), "52be80"),
    NOASIGNADO (String.valueOf("No Asignado"), "de0707"),
    ASIGNADO (String.valueOf("Asignado"), "52be80");

	
	ColoresEnum(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public static String getNombreById(String nombre) {
        for (ColoresEnum e : ColoresEnum.values()) {
            if (e.nombre.equals(nombre)) {
                return e.getCodigo();
            }
        }

        return "52166b";
    }
    public static String getNombreById(Integer nombre) {
        for (ColoresEnum e : ColoresEnum.values()) {
            if (e.nombre.equals(nombre)) {
                return e.getCodigo();
            }
        }

        return "52166b";
    }

    private String nombre;
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
}
