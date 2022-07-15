export class Paciente {
    id?: number;
    DNI: number;
    nombre: string;
    apellido: string;
    localidad: string;
    direccion: string;
    direccionNro: number;
    telefono: number;

    constructor(DNI: number, nombre: string, apellido: string, localidad: string, direccion: string, direccionNro: number, telefono: number ) {
    
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
        this.direccion = direccion;
        this.direccionNro = direccionNro;
        this.telefono = telefono;
    
    }
    
}

