package com.proaula.demo.domain.common;

public class MensajesError {

    // registro - usuario
    public static final String EMAIL_YA_EXISTE = "Este email ya existe en la base de datos";
    public static final String REGISTRO_CORRECTO = "Usuario registrado con éxito";
    public static final String ERROR_BD_REGISTRAR = "Error al registrar al usuario en la base de datos";

    // login - usuario
    public static final String INICIO_SESION_EXITOSO = "Inicio de sesión exitoso";
    public static final String CREDENCIALES_INVALIDAS = "Email, contraseña o tipo de usuario incorrectos";
    public static final String ERROR_BD_INICIAR = "Error al loguarse en la base de datos";

    // actualizar - usuario
    public static final String USUARIO_NO_ENCONTRADO = "Usuario no encontrado";
    public static final String ACTUALIZACION_CORRECTA = "Usuario actualizado con éxito";
    public static final String ERROR_BD_ACTUALIZAR = "Error al actualizar el usuario en la base de datos";

    // obtener - usuario
    public static final String USUARIOS_NO_ENCONTRADOS = "Usuarios no encontrados";
    public static final String CONSULTA_EXITOSA = "Consulta de usuarios exitosa";
    public static final String ERROR_CONSULTA = "Error al consultar en la bd";

    // registro - producto
    public static final String PRODUCTO_YA_EXISTE = "Este producto ya existe en la base de datos";
    public static final String REGISTRO_PRODUCTO_CORRECTO = "Producto registrado con éxito";
    public static final String ERROR_BD_REGISTRAR_PRODUCTO = "Error al registrar el producto en la base de datos";

    // actualizar - producto
    public static final String PRODUCTO_NO_ENCONTRADO = "Producto no encontrado";
    public static final String ACTUALIZACION_PRODUCTO_CORRECTA = "Producto actualizado con éxito";

    // obtener - producto
    public static final String PRODUCTOS_NO_ENCONTRADOS = "Productos no encontrados";
    public static final String CONSULTA_PRODUCTO_EXITOSA = "Consulta de productos exitosa";
    public static final String ERROR_CONSULTA_PRODUCTO = "Error al consultar los productos en la bd";

    // eliminar - producto
    public static final String ELIMINACION_PRODUCTO_CORRECTA = "Producto eliminado con éxito";
    public static final String ERROR_BD_ELIMINAR_PRODUCTO = "Error al eliminar el producto en la base de datos";

    // comentario 
    public static final String REGISTRO_COMENTARIO_CORRECTO = "Comentario registrado correctamente";
    public static final String ERROR_BD_REGISTRAR_COMENTARIO = "Error al registrar el comentario en la base de datos";
    public static final String COMENTARIOS_ENCONTRADOS = "Comentarios encontrados";
    public static final String NO_COMENTARIOS_ENCONTRADOS = "No se encontraron comentarios";
    public static final String ERROR_BD_OBTENER_COMENTARIOS = "Error al obtener los comentarios de la base de datos";

}
