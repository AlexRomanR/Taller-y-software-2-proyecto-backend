package com.GestionAsisDocente.seeders;

import com.GestionAsisDocente.entity.*;
import com.GestionAsisDocente.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private FacultadesRepository facultadesRepository;
    @Autowired
    private UsuarioRepo ourUserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModulosRepository modulosRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PermisosRepository permisosRepository;
    @Autowired
    private AulasRepository aulasRepository;
    @Autowired
    private CarrerasRepository carrerasRepository;
    @Autowired
    private MateriasRepository materiasRepository;
    @Autowired
    private RolUserRepository rolUserRepository;
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private PublicacionesRepository publicacionesRepository;

    @Autowired
    private ComentariosRepository comentariosRepository;

    private void createPermisos() {
        permisosRepository.save(createPermiso("Listar Usuarios"));
        permisosRepository.save(createPermiso("Editar Usuarios"));
        permisosRepository.save(createPermiso("Eliminar Usuarios"));
        permisosRepository.save(createPermiso("Crear Usuarios"));

        permisosRepository.save(createPermiso("Listar Docentes"));
        permisosRepository.save(createPermiso("Editar Docentes"));
        permisosRepository.save(createPermiso("Eliminar Docentes"));
        permisosRepository.save(createPermiso("Crear Docentes"));

        permisosRepository.save(createPermiso("Listar Materias"));
        permisosRepository.save(createPermiso("Editar Materias"));
        permisosRepository.save(createPermiso("Eliminar Materias"));
        permisosRepository.save(createPermiso("Crear Materias"));

        permisosRepository.save(createPermiso("Listar Carreras"));
        permisosRepository.save(createPermiso("Editar Carreras"));
        permisosRepository.save(createPermiso("Eliminar Carreras"));
        permisosRepository.save(createPermiso("Crear Carreras"));

        permisosRepository.save(createPermiso("Listar Facultades"));
        permisosRepository.save(createPermiso("Editar Facultades"));
        permisosRepository.save(createPermiso("Eliminar Facultades"));
        permisosRepository.save(createPermiso("Crear Facultades"));

        permisosRepository.save(createPermiso("Listar Modulos"));
        permisosRepository.save(createPermiso("Editar Modulos"));
        permisosRepository.save(createPermiso("Eliminar Modulos"));
        permisosRepository.save(createPermiso("Crear Modulos"));

        permisosRepository.save(createPermiso("Listar Aulas"));
        permisosRepository.save(createPermiso("Editar Aulas"));
        permisosRepository.save(createPermiso("Eliminar Aulas"));
        permisosRepository.save(createPermiso("Crear Aulas"));
    }

    private Permisos createPermiso(String nombre) {
        Permisos permiso = new Permisos();
        permiso.setNombre(nombre);
        return permiso;
    }

    @Override
    public void run(String... args) throws Exception {
        if (permisosRepository.count() == 0) {
            createPermisos();
        }
        // Facultades
        if (facultadesRepository.count() == 0) {
            Facultades facultad1 = new Facultades();
            facultad1.setNombre("Facultad de Ingeniería en Ciencias de la Computación");
            facultad1.setDescripcion("Facultad dedicada a la programación y gestión de sistemas");

            Facultades facultad2 = new Facultades();
            facultad2.setNombre("Facultad de Ciencias Empresariales");
            facultad2.setDescripcion("Facultad dedicada a la administración y gestión empresarial");

            facultadesRepository.save(facultad1);
            facultadesRepository.save(facultad2);
        }


        // Roles
        if (rolesRepository.count() == 0) {
            // Obtener todos los permisos ya creados
            List<Permisos> permisos = permisosRepository.findAll();

            // Crear el rol y asignar todos los permisos obtenidos
            Roles rolAdmin = new Roles();
            rolAdmin.setNombre("ADMIN");
            rolesRepository.save(rolAdmin);
            rolAdmin.setPermissions(permisos);
            rolesRepository.save(rolAdmin);


            // Filtrar los permisos para el rol "USERS"
            List<Permisos> permisosUsuarios = new ArrayList<>();
            for (Permisos permiso : permisosRepository.findAll()) {
                if (permiso.getNombre().startsWith("Listar") &&
                        !permiso.getNombre().equals("Listar Usuarios") &&
                        !permiso.getNombre().equals("Listar Docentes")) {
                    permisosUsuarios.add(permiso);
                }
            }

            // Crear el rol "USERS" y asignar los permisos filtrados
            Roles rolUsers = new Roles();
            rolUsers.setNombre("USERS");
            rolUsers.setPermissions(permisosUsuarios);

            // Guardar el rol "USERS" en el repositorio
            rolesRepository.save(rolUsers);
        }

        // Usuarios
        if (ourUserRepo.count() == 0) {
            Usuario ourUsers1 = new Usuario();
            ourUsers1.setEmail("admin@gmail.com");
            ourUsers1.setName("admin");
            ourUsers1.setPassword(passwordEncoder.encode("12345678"));


            Usuario ourUsers2 = new Usuario();
            ourUsers2.setEmail("user@gmail.com");
            ourUsers2.setName("user");
            ourUsers2.setPassword(passwordEncoder.encode("12345678"));


            ourUserRepo.save(ourUsers1);
            ourUserRepo.save(ourUsers2);

            Usuario admin = ourUserRepo.findByEmail("admin@gmail.com").orElseThrow();
            Roles rolAdmin = rolesRepository.findByNombre("ADMIN").orElseThrow();

            Usuario user = ourUserRepo.findByEmail("user@gmail.com").orElseThrow();
            Roles rolUserr = rolesRepository.findByNombre("USERS").orElseThrow();

            // Asignar rol al admin
            RolUser rolUserAdmin = new RolUser();
            rolUserAdmin.setUsuario(admin);
            rolUserAdmin.setRol(rolAdmin);
            rolUserRepository.save(rolUserAdmin);

            // Asignar rol al usuario normal
            RolUser rolUserNormal = new RolUser();
            rolUserNormal.setUsuario(user);
            rolUserNormal.setRol(rolUserr);
            rolUserRepository.save(rolUserNormal);
        }

        // Modulos
        if (modulosRepository.count() == 0) {
            Modulos modulos1 = new Modulos();
            modulos1.setNumero(256);
            modulos1.setDescripcion("Modulo de la facultad de ciencias empresariales");
            modulos1.setUbicacion("-17.818444166536366,-63.099470599823015");

            Modulos modulos2 = new Modulos();
            modulos2.setNumero(213);
            modulos2.setDescripcion("Modulo de la facultad de computación");
            modulos2.setUbicacion("-17.818444166536366,-63.099470599823015");

            Facultades facultad1 = facultadesRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Facultad not found with id 1"));

            Facultades facultad2 = facultadesRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Facultad not found with id 2"));

            modulos1.setFacultad(facultad1);
            modulos2.setFacultad(facultad2);

            modulosRepository.save(modulos1);
            modulosRepository.save(modulos2);
        }

        // Aulas
        if (aulasRepository.count() == 0) {
            Aulas aulas1 = new Aulas();
            aulas1.setCapacidad(65);
            aulas1.setNumero(35);
            aulas1.setPiso(3);
            aulas1.setDescripcion("Aula para avanzar teoria");

            Modulos modulo1 = modulosRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("Modulo not found with id 1"));

            aulas1.setModulos(modulo1);



            aulasRepository.save(aulas1);

        }

        // carreras
        if (carrerasRepository.count() == 0) {
            Carreras carreras1 = new Carreras();
            carreras1.setCodigo("ING.SIS");
            carreras1.setNombre("Ingenieria en sistemas");

            carrerasRepository.save(carreras1);

        }

        // Materias
        if (materiasRepository.count() == 0) {
            Materias materias1 = new Materias();
            materias1.setSigla("SI2");
            materias1.setNombre("Sistemas de información 2");

            materiasRepository.save(materias1);

        }

        //tipo
        if (tipoRepository.count() == 0) {
            Tipo tipoVisto = new Tipo();
            tipoVisto.setNombre("Visto");

            Tipo tipoBuscando = new Tipo();
            tipoBuscando.setNombre("Buscando");

            tipoRepository.save(tipoVisto);
            tipoRepository.save(tipoBuscando);

            System.out.println("Tipos iniciales creados: Visto, Buscando");
        }

        // Permisos
        if (permisosRepository.count() == 0) {
            createPermisos();
        }


        //publicaciones
        // publicaciones
        if (publicacionesRepository.count() == 0) {

            // Obtener tipos existentes
            Tipo tipoBuscando = tipoRepository.findByNombre("Buscando")
                    .orElseThrow(() -> new RuntimeException("Tipo 'Buscando' no encontrado"));

            Tipo tipoVisto = tipoRepository.findByNombre("Visto")
                    .orElseThrow(() -> new RuntimeException("Tipo 'Visto' no encontrado"));

            // Obtener usuarios existentes
            List<Usuario> usuarios = ourUserRepo.findAll();
            if (usuarios.isEmpty()) throw new RuntimeException("No hay usuarios en la base de datos");

            Usuario user1 = usuarios.get(0);
            Usuario user2 = usuarios.size() > 1 ? usuarios.get(1) : user1;

            List<Publicaciones> publicaciones = new ArrayList<>();

            // 1) VISTO - visible
            Publicaciones pub1 = new Publicaciones();
            pub1.setTitulo("Perro encontrado en el parque central");
            pub1.setDescripcion("Se encontró un perro marrón de tamaño mediano, con collar azul.");
            pub1.setFecha(LocalDate.now().minusDays(2));
            pub1.setHora(LocalTime.of(14, 30));
            pub1.setEstado(true);
            pub1.setUbicacion("-17.783300, -63.182100"); // Plaza principal
            pub1.setArchivo("https://media.istockphoto.com/id/1724603954/es/vector/anuncio-de-aviso-de-cartel-de-animal-de-compa%C3%B1%C3%ADa-desaparecido-perdido-en-el-concepto-de.jpg");
            pub1.setTipo(tipoVisto);
            pub1.setUsuario(user1);
            publicaciones.add(pub1);

            // 2) BUSCANDO - visible
            Publicaciones pub2 = new Publicaciones();
            pub2.setTitulo("Busco perro perdido cerca de mi casa");
            pub2.setDescripcion("Mi perro se perdió ayer, es pequeño y blanco con manchas negras.");
            pub2.setFecha(LocalDate.now().minusDays(1));
            pub2.setHora(LocalTime.of(10, 15));
            pub2.setUbicacion("-17.779500, -63.180000"); // zona residencial
            pub2.setEstado(true);
            pub2.setArchivo("https://media.istockphoto.com/id/1724603954/es/vector/anuncio-de-aviso-de-cartel-de-animal-de-compa%C3%B1%C3%ADa-desaparecido-perdido-en-el-concepto-de.jpg");
            pub2.setTipo(tipoBuscando);
            pub2.setUsuario(user1);
            publicaciones.add(pub2);

            // 3) BUSCANDO - visible
            Publicaciones pub3 = new Publicaciones();
            pub3.setTitulo("Gatito perdido por la plaza principal");
            pub3.setDescripcion("Gato negro con mancha blanca en el pecho, muy asustadizo. Se perdió anoche.");
            pub3.setFecha(LocalDate.now().minusDays(1));
            pub3.setHora(LocalTime.of(21, 5));
            pub3.setUbicacion("-17.784200, -63.180800");
            pub3.setEstado(true);
            pub3.setArchivo("https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg");
            pub3.setTipo(tipoBuscando);
            pub3.setUsuario(user2);
            publicaciones.add(pub3);

            // 4) BUSCANDO - visible
            Publicaciones pub4 = new Publicaciones();
            pub4.setTitulo("Perra golden retriever perdida en Equipetrol");
            pub4.setDescripcion("Se llama Luna, lleva un pañuelo rosado en el cuello. Última vez vista cerca de un café.");
            pub4.setFecha(LocalDate.now().minusDays(3));
            pub4.setHora(LocalTime.of(18, 45));
            pub4.setUbicacion("-17.770500, -63.184300");
            pub4.setEstado(true);
            pub4.setArchivo("https://images.pexels.com/photos/2253275/pexels-photo-2253275.jpeg");
            pub4.setTipo(tipoBuscando);
            pub4.setUsuario(user1);
            publicaciones.add(pub4);

            // 5) VISTO - visible
            Publicaciones pub5 = new Publicaciones();
            pub5.setTitulo("Gato visto en el techo de una casa");
            pub5.setDescripcion("Gatito blanco con cola gris, lleva un collar rojo. Lo vi en el techo del tercer anillo.");
            pub5.setFecha(LocalDate.now().minusDays(4));
            pub5.setHora(LocalTime.of(16, 10));
            pub5.setUbicacion("-17.786800, -63.168900");
            pub5.setEstado(true);
            pub5.setArchivo("https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg");
            pub5.setTipo(tipoVisto);
            pub5.setUsuario(user2);
            publicaciones.add(pub5);

            // 6) BUSCANDO - visible
            Publicaciones pub6 = new Publicaciones();
            pub6.setTitulo("Perro mestizo perdido por el mercado");
            pub6.setDescripcion("Perro de tamaño grande, pelaje negro con café, muy amigable. Responde al nombre 'Rocco'.");
            pub6.setFecha(LocalDate.now().minusDays(5));
            pub6.setHora(LocalTime.of(11, 20));
            pub6.setUbicacion("-17.791200, -63.194300");
            pub6.setEstado(true);
            pub6.setArchivo("https://images.pexels.com/photos/4608267/pexels-photo-4608267.jpeg");
            pub6.setTipo(tipoBuscando);
            pub6.setUsuario(user1);
            publicaciones.add(pub6);

            // 7) BUSCANDO - visible
            Publicaciones pub7 = new Publicaciones();
            pub7.setTitulo("Perrito pequeño desaparecido en el cuarto anillo");
            pub7.setDescripcion("Muy juguetón, color beige, llevaba un suéter azul cuando salió.");
            pub7.setFecha(LocalDate.now().minusDays(2));
            pub7.setHora(LocalTime.of(9, 0));
            pub7.setUbicacion("-17.802300, -63.204500");
            pub7.setEstado(true);
            pub7.setArchivo("https://images.pexels.com/photos/5731861/pexels-photo-5731861.jpeg");
            pub7.setTipo(tipoBuscando);
            pub7.setUsuario(user2);
            publicaciones.add(pub7);

            // 8) VISTO - NO visible (oculto)
            Publicaciones pub8 = new Publicaciones();
            pub8.setTitulo("Gato visto cerca de la universidad");
            pub8.setDescripcion("Gato atigrado, parecía perdido pero se veía bien cuidado.");
            pub8.setFecha(LocalDate.now().minusDays(6));
            pub8.setHora(LocalTime.of(13, 40));
            pub8.setUbicacion("-17.812900, -63.167000");
            pub8.setEstado(false); // oculto
            pub8.setArchivo("https://images.pexels.com/photos/1276553/pexels-photo-1276553.jpeg");
            pub8.setTipo(tipoVisto);
            pub8.setUsuario(user1);
            publicaciones.add(pub8);

            // 9) BUSCANDO - NO visible (oculto)
            Publicaciones pub9 = new Publicaciones();
            pub9.setTitulo("Buscando a mi perrita en zona norte");
            pub9.setDescripcion("Se llama Kira, lleva un collar con placa, es muy tímida con desconocidos.");
            pub9.setFecha(LocalDate.now().minusDays(7));
            pub9.setHora(LocalTime.of(19, 30));
            pub9.setUbicacion("-17.795000, -63.170000");
            pub9.setEstado(false); // oculto
            pub9.setArchivo("https://images.pexels.com/photos/7210751/pexels-photo-7210751.jpeg");
            pub9.setTipo(tipoBuscando);
            pub9.setUsuario(user2);
            publicaciones.add(pub9);

            // 10) BUSCANDO - visible
            Publicaciones pub10 = new Publicaciones();
            pub10.setTitulo("Perrito anciano perdido cerca del parque urbano");
            pub10.setDescripcion("Camina despacio, tiene un ojo nublado, necesita medicación diaria.");
            pub10.setFecha(LocalDate.now().minusDays(1));
            pub10.setHora(LocalTime.of(7, 50));
            pub10.setUbicacion("-17.789900, -63.181500");
            pub10.setEstado(true);
            pub10.setArchivo("https://images.pexels.com/photos/2817405/pexels-photo-2817405.jpeg");
            pub10.setTipo(tipoBuscando);
            pub10.setUsuario(user1);
            publicaciones.add(pub10);

            publicacionesRepository.saveAll(publicaciones);

            System.out.println("Seeder de publicaciones completado. Total publicaciones: " + publicaciones.size());
        }


        if (comentariosRepository.count() == 0) {
            List<Usuario> usuarios = ourUserRepo.findAll();
            List<Publicaciones> publicaciones = publicacionesRepository.findAll();
            Random random = new Random();

            String[] comentariosEjemplo = {
                    "Yo también lo vi",
                    "Pobre perrito",
                    "Gracias por la información",
                    "Espero que lo encuentren pronto",
                    "¡Qué triste!",
                    "Ojalá alguien pueda ayudar",
                    "Lo vi cerca de la plaza"
            };

            for (Publicaciones pub : publicaciones) {
                int comentariosCount = 2 + random.nextInt(4);
                for (int i = 0; i < comentariosCount; i++) {
                    Comentarios comentario = new Comentarios();
                    comentario.setPublicacion(pub);

                    Usuario usuario = usuarios.get(random.nextInt(usuarios.size()));
                    comentario.setUsuario(usuario);

                    comentario.setTexto(comentariosEjemplo[random.nextInt(comentariosEjemplo.length)]);
                    comentario.setArchivo("comentario_archivo_" + i + ".jpg");
                    comentario.setIncognito(random.nextBoolean());
                    comentario.setLikes(random.nextInt(10));

                    comentariosRepository.save(comentario);
                }
            }

            System.out.println("Seeder de comentarios con estilo realista ejecutado correctamente.");
        }


    }




}

