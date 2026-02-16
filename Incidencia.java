import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidencia {
    private int id;
    private String descripcion;
    private Date fechaRegistro;
    private String nivelPrioridad;

    public Incidencia(int id, String descripcion, String fechaStr, String nivelPrioridad)
            throws DescripcionInvalidaException, FechaInvalidaException, PrioridadInvalidaException {
        this.id = id;
        validarYSetDescripcion(descripcion);
        validarYSetFecha(fechaStr);
        validarYSetPrioridad(nivelPrioridad);
    }

    private void validarYSetDescripcion(String desc) throws DescripcionInvalidaException {
        if (desc == null || desc.trim().isEmpty()) {
            throw new DescripcionInvalidaException("La descripcion no puede estar vacia o tener solo espacios.");
        }
        if (desc.trim().length() < 10) {
            throw new DescripcionInvalidaException("La descripcion debe tener al menos 10 caracteres reales.");
        }
        this.descripcion = desc.trim();
    }

    private void validarYSetFecha(String fechaStr) throws FechaInvalidaException {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new FechaInvalidaException("La fecha no puede estar vacia.");
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        
        try {
            Date fechaParseada = sdf.parse(fechaStr.trim());
            if (fechaParseada.after(new Date())) {
                throw new FechaInvalidaException("La fecha de registro no puede ser en el futuro.");
            }
            this.fechaRegistro = fechaParseada;
        } catch (ParseException e) {
            throw new FechaInvalidaException("Formato de fecha incorrecto. Debe ser dd/MM/yyyy.");
        }
    }

    private void validarYSetPrioridad(String prio) throws PrioridadInvalidaException {
        if (prio == null) {
            throw new PrioridadInvalidaException("La prioridad no puede ser nula.");
        }
        
        String prioLimpia = prio.trim().toUpperCase();
        
        if (prioLimpia.equalsIgnoreCase("ALTA") ||
            prioLimpia.equalsIgnoreCase("MEDIA") ||
            prioLimpia.equalsIgnoreCase("BAJA")) {
            this.nivelPrioridad = prioLimpia;
        } else {
            throw new PrioridadInvalidaException("La prioridad solo puede ser ALTA, MEDIA o BAJA.");
        }
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public String getNivelPrioridad() { return nivelPrioridad; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("[ID: %d] | Fecha: %s | Prioridad: %s | Descripción: %s",
                id, sdf.format(fechaRegistro), nivelPrioridad, descripcion);
    }
}