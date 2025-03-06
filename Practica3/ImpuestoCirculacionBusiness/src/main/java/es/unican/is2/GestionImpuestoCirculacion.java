package es.unican.is2;

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IGestionVehiculos, IGestionContribuyentes {

	private IContribuyentesDAO cont;
	private IVehiculosDAO vehic;

	public GestionImpuestoCirculacion (IContribuyentesDAO cont, IVehiculosDAO vehic) {
		this.cont = cont;
		this.vehic = vehic;
	}

    /**
	 * Registra un nuevo contribuyente
	 * @param c Contribuyente que desea registrar
	 * @return El contribuyente registrado
	 * 		   null si no se registra porque ya existe un 
	 *              contribuyente con el mismo dni
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		//TODO
        return null;
    }

    /**
	 * Elimina el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente que se quiere eliminar
	 * @return El contribuyente eliminado
	 * 		   null si no se elimina porque no se encuentra 
	 * @throws OperacionNoValidaException si el contribuyente existe 
	 *         pero tiene vehiculos a su nombre
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
        //TODO
		return null;
    }

    /**
	 * Registra un nuevo vehiculo y lo asocia al contribuyente con el dni indicado
	 * @param v Vehiculo que desea registrar
	 * @param dni DNI del contribuyente
	 * @return El vehiculo ya registrado
	 * 		   null si no se registra porque no se encuentra el contribuyente
	 * @throws OperacionNoValidaException si ya existe un vehiculo con la misma matricula
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
        //TODO
		return null;
    }

    /**
	 * Elimina el vehiculo cuya matricula se indica y 
	 * que pertenece al contribuyente cuyo dni se indica
	 * @param matricula Matricula del coche a eliminar
	 * @param dni DNI del propietario del vehiculo
 	 * @return El vehiculo eliminado
 	 *         null si el vehiculo o el propietario no existen
 	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
 	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
        //TODO
		return null;
    }

    /**
	 * Cambia el propietario del vehiculo indicado
	 * @param matricula Matricula del vehiculo
	 * @param dniActual DNI del propietario actual del vehiculo
	 * @param dniNuevo DNI del nuevo propietario del vehiculo
	 * @return true si se realiza el cambio
	 *         false si no realiza el cambio porque el vehiculo o los contribuyentes no existen
	 * @throws OperacionNoValidaException si el vehiculo no pertenece al dni indicado
	 * @throws DataAccessException Si hay error en el acceso a los datos
	 */
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws OperacionNoValidaException, DataAccessException {
        //TODO
		return false;
    }

    /**
	 * Retorna el vehiculo cuya matricula se indica
	 * @param matricula Matricula del vehiculo buscado
	 * @return El vehiculo correspondiente a la matricula
	 * 	       null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
        //TODO
		return null;
    }

    /**
	 * Retorna el contribuyente cuyo dni se indica
	 * @param dni DNI del contribuyente buscado
	 * @return El contribuyente correspondiente al dni
	 * 		   null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Contribuyente contribuyente(String dni) throws DataAccessException {
        //TODO
		return null;
    }
}