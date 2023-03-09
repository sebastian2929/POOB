/* CICLO 1 */

CREATE Table usuarios(
    ID NUMBER(5) NOT NULL,
    fechaRegistro DATE ,
    correoElectronico VARCHAR(50) NOT NULL
);

CREATE Table numeroscontactos(
    ID NUMBER(5) NOT NULL,
    Contacto NUMERIC(10) NOT NULL
);

CREATE Table personasnaturales(
    ID NUMBER(5) NOT NULL,
    tipoDocumento VARCHAR(2) NOT NULL,
    numeroDocumento VARCHAR(30)  NOT NULL,
    nombresCompleto VARCHAR(50) NOT NULL,
    nacionalidad VARCHAR(10) NOT NULL
);

CREATE Table empresas(
    ID NUMBER(5) NOT NULL,
    nit VARCHAR(10) NOT NULL,
    razonSocial VARCHAR(100) NOT NULL,
    persona NUMBER(5) NOT NULL
);

CREATE TABLE ofertas(
    numero NUMBER(9) NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(500) NOT NULL,
    tipovivienda VARCHAR(1) NOT NULL,
    tamaño NUMBER(3) NOT NULL,
    habitada VARCHAR(1) NOT NULL,
    costo NUMBER(9) NOT NULL,
    proyectovivienda VARCHAR(1) NOT NULL,
    anexos VARCHAR(50),
    usuario NUMBER(5) NOT NULL,
    ubicacion VARCHAR(11) NOT NULL
);

CREATE TABLE ofertas_fotografias(
    nombre VARCHAR(15) NOT NULL,
    numero NUMBER(9) NOT NULL
);

CREATE TABLE fotografias(
    nombref VARCHAR(15) NOT NULL,
    ruta VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE opcionescreditos(
    numero NUMBER(9) NOT NULL,
    plazo NUMBER NOT NULL,
    valormensual NUMBER(9) NOT NULL
);

CREATE TABLE ubicaciones(
    codigo VARCHAR(11) NOT NULL,
    latitud NUMBER(3) NOT NULL,
    longitud NUMBER(3) NOT NULL,
    ciudad VARCHAR(10) NOT NULL,
    zona VARCHAR(1) NOT NULL,
    barrio VARCHAR(10) NOT NULL
);

CREATE TABLE interesesen(
    ubicacion VARCHAR(11) NOT NULL,
    demanda NUMBER(9) NOT NULL,
    nivel VARCHAR(1) NOT NULL
);

CREATE TABLE demanda(
    numero NUMBER(9) NOT NULL,
    fecha DATE NOT NULL,
    tipoVivienda VARCHAR(1) NOT NULL,
    maxCompra NUMBER(9) NOT NULL,
    usuario NUMBER(5) NOT NULL
);

CREATE TABLE origenesfondos(
    valor NUMBER(9) NOT NULL,
    credito VARCHAR(1) NOT NULL,
    estaaprobado VARCHAR(1),
    entidadbancaria VARCHAR(50),
    demanda NUMBER(9) NOT NULL
);

/*
"CRUD:--------------------"
    ATRIBUTOS
    TUPLAS "Todas las demandas corresponden a un unico pago en efectivo" */
    ALTER TABLE ORIGENESFONDOS ADD CONSTRAINT UK_Pagos_Efectivo UNIQUE(credito);
/*  TUPLASOK         */
    INSERT INTO origenesfondos VALUES(00001,'t','t','Bancolombia',98765432);
/*  TUPLASNOOK       */
    INSERT INTO origenesfondos(valor,credito,estaaprobado,entidadbancaria,demanda) VALUES(12345678,'t','t','Bancolombia',98765432);

/*----------------------------------------------------------------
--Disparadores */
--/* Las demandas no se pueden eliminar */
--
--    CREATE TRIGGER TR_NO_ELIMINAR
--    BEFORE DELETE
--    ON DEMANDA
--    FOR EACH ROW
--    BEGIN NO_ERROR(-5,'no se puede eliminar');
--
--/*Las descripciones no se pueden eliminar*/ 
--    CREATE OR REPLACE TRIGGER TR_NO_ELIMINAR_DESCRIPCION
--    BEFORE DELETE
--    ON OFERTA
--    FOR EACH ROW
--    BEGIN NO_ERROR(-5,'no se puede eliminar')
--
--/*DISPARADORNOOK*/
--    DROP TABLE DEMANDA CASCADE CONSTRAINT PURGE;
--    DROP TABLE OFERTA CASCADE CONSTRAINT PURGE;
--
--/*Xdisparadores*/
--    DROP TRIGGER TR_NO_ELIMINAR
--    DROP TRIGGER TR_NO_ELIMINAR_DESCRIPCION

/*----------------------------------------------------------------*/
    /*PROTEGIENDO*/
    /*Ciclo 1 PRIMARIAS*/
ALTER TABLE usuarios ADD CONSTRAINT PK_usuario PRIMARY KEY(ID);
ALTER TABLE numeroscontactos ADD CONSTRAINT PK_numeroscontactos PRIMARY KEY(ID);
ALTER TABLE personasnaturales ADD CONSTRAINT PK_personasnaturales PRIMARY KEY(ID);
ALTER TABLE empresas ADD CONSTRAINT PK_empresas PRIMARY KEY(ID);
ALTER TABLE ofertas ADD CONSTRAINT PK_ofertas PRIMARY KEY(numero);
ALTER TABLE ofertas_fotografias ADD CONSTRAINT PK_ofertas_fotografias PRIMARY KEY(nombre,numero);
ALTER TABLE fotografias ADD CONSTRAINT PK_Product PRIMARY KEY(nombref);
ALTER TABLE opcionescreditos ADD CONSTRAINT PK_fotografias PRIMARY KEY(numero);
ALTER TABLE ubicaciones ADD CONSTRAINT PK_ubicaciones PRIMARY KEY(codigo);
ALTER TABLE interesesen ADD CONSTRAINT PK_interesesen PRIMARY KEY(ubicacion,demanda);
ALTER TABLE demanda ADD CONSTRAINT PK_demanda PRIMARY KEY(numero);
    
    /*Ciclo 1 Unicas*/
ALTER TABLE personasnaturales ADD CONSTRAINT UK_personasnaturales_num UNIQUE (numeroDocumento);
ALTER TABLE empresas ADD CONSTRAINT UK_empresas UNIQUE (nit);

    /*Ciclo 1 Foraneas*/

ALTER TABLE numeroscontactos 
    ADD CONSTRAINT FK_numeroscontactos
    FOREIGN KEY(ID) 
    REFERENCES usuarios(ID);

ALTER TABLE personasnaturales 
    ADD CONSTRAINT FK_personasnaturales
    FOREIGN KEY(ID) 
    REFERENCES usuarios(ID);

ALTER TABLE empresas 
    ADD CONSTRAINT FK_empresas
    FOREIGN KEY(ID) 
    REFERENCES usuarios(ID);

ALTER TABLE ofertas 
    ADD CONSTRAINT FK_ofertas
    FOREIGN KEY(usuario) 
    REFERENCES usuarios(ID);
    
ALTER TABLE demanda
    ADD CONSTRAINT FK_demanda
    FOREIGN KEY(usuario) 
    REFERENCES usuarios(ID);

ALTER TABLE ofertas
    ADD CONSTRAINT FK_ofertas_u
    FOREIGN KEY(ubicacion) 
    REFERENCES ubicaciones(codigo);

ALTER TABLE empresas 
    ADD CONSTRAINT FK_empresas_p
    FOREIGN KEY(persona) 
    REFERENCES personasnaturales(ID);
    
ALTER TABLE ofertas_fotografias 
    ADD CONSTRAINT FK_ofertas_fotografias
    FOREIGN KEY(nombre) 
    REFERENCES fotografias(nombref);
    
ALTER TABLE ofertas_fotografias 
    ADD CONSTRAINT FK_ofertas_fotografias_num
    FOREIGN KEY(numero) 
    REFERENCES ofertas(numero);
    
ALTER TABLE opcionescreditos 
    ADD CONSTRAINT FK_opcionescreditos
    FOREIGN KEY(numero) 
    REFERENCES ofertas(numero);

ALTER TABLE interesesen 
    ADD CONSTRAINT FK_interesesen
    FOREIGN KEY(ubicacion) 
    REFERENCES ubicaciones(codigo);

ALTER TABLE interesesen 
    ADD CONSTRAINT FK_interesesen_num
    FOREIGN KEY(demanda) 
    REFERENCES demanda(numero);
    
ALTER TABLE origenesfondos 
    ADD CONSTRAINT FK_origenesfondos
    FOREIGN KEY(demanda) 
    REFERENCES demanda(numero);
    
/*-----------------------------------------------------------------------------------------*/
/* POBLANDO */
    /*PoblarOK(1)*/
        /*Usuarios*/
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(1,to_date('2019-DEC-20 13:35','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'sebastian@gmail.com');
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(2,to_date('2018-DEC-01 8:12','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'jose@gmail.com');
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(3,to_date('2018-DEC-14 19:47','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'pablo@gmail.com');
        /*Oferta*/
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) VALUES(001,to_date('01-05-2022', 'dd-mm-yyyy'),'una apartamento de 1 baño','a',100,'t',10000,'f','anexos',2,'10');
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) VALUES(002,to_date('18-06-2021', 'dd-mm-yyyy'),'una casa de 3 baño','c',100,'t',2000000,'f','anexos',2,'10');
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) VALUES(003,to_date('29-03-2022', 'dd-mm-yyyy'),'una casa de 2 baño','c',100,'f',100000,'f','anexos',2,'10');

        /*Ubicacion*/
INSERT INTO ubicaciones(codigo,latitud,longitud,ciudad,zona,barrio) VALUES ('00000000001',30,40,'Bogota','N','mirandela');
INSERT INTO ubicaciones(codigo,latitud,longitud,ciudad,zona,barrio) VALUES ('00000000002',90,20,'Medellin','S','Poblado');
INSERT INTO ubicaciones(codigo,latitud,longitud,ciudad,zona,barrio) VALUES ('00000000003',50,10,'Bogota','O','Chapinero');
        /*Demanda*/
INSERT INTO demanda(numero,fecha,tipoVivienda,maxCompra,usuario) VALUES ('100',to_date('01-05-2022', 'dd-mm-yyyy'),'c',10000000,1);
INSERT INTO demanda(numero,fecha,tipoVivienda,maxCompra,usuario) VALUES ('200',to_date('08-06-2021', 'dd-mm-yyyy'),'c',99999900,2);
INSERT INTO demanda(numero,fecha,tipoVivienda,maxCompra,usuario) VALUES ('300',to_date('29-04-2022', 'dd-mm-yyyy'),'a',999999999,3);

    /* Poblar(2 y 3) */
    
    /*2*/
--La fecha no esta bien 
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(2,to_date('2018-DEC-01','YYYY-MON-DD'),'jose@gmail.com');
--La ubicacion no cumple con la longitud establecida
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usuario,ubicacion) VALUES(1000000000,to_date('01-05-2022', 'dd-mm-yyyy'),'una apartamento de 1 baño','a',100,'t',10000,'f','anexos',2,'10');
--Inserta un valor null cuando este tiene que existir
INSERT INTO ubicaciones(codigo,latitud,longitud,ciudad,zona,barrio) VALUES('00000000003',50,10,'Bogota',NULL,'Chapinero');
--Mala insercion de la fecha
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(1,to_date('2019-DEC-32 13:35','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'sebastian@gmail.com');
--Se agrega un null cuando no se puede ademas de poner una cadena mas larga de la permitida
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) VALUES(003,to_date('29-03-2021', 'dd-mm-yyyy'),NULL,'casa',100,'f',100000,'f','anexos',2,'10');

    /*3*/
--En este no existe el ID
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(to_date('2019-DEC-20 13:35','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'sebastianB@gmail.com');
--No tiene correo
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(3,to_date('2018-DEC-14 19:47','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'));
--Le falta tener un barrio
INSERT INTO ubicaciones(codigo,latitud,longitud,ciudad,zona,barrio) VALUES ('00000000003',50,10,'Bogota','Centro');
--No se sabe si esta habitada por un error en el dato
INSERT INTO ofertas(numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) VALUES(3,to_date('29-04-2022', 'dd-mm-yyyy'),'apartamento 1 habitaciones',1,'a',750000.0,'t','0','3','00000000001');
--No tiene dueño 
INSERT INTO demanda(numero,fecha,tipoVivienda,maxCompra,usuario) VALUES ('2000000000',to_date('08-06-2021', 'dd-mm-yyyy'),'casa',500000000.0);

    /*CONSULTADO*/
SELECT nombres, apellidos
FROM personasnaturales
JOIN usuarios ON personasnaturales.ID = usuarios.ID
JOIN ofertas ON usuarios.ID = ofertas.usuario
WHERE costo > 500000.0;

/*Consultar el valor de los origenes de fondo*/
SELECT
    "A1"."VALOR" "VALOR"
FROM
    "BD1000046615"."ORIGENESFONDOS" "A1"
WHERE
    "A1"."VALOR" > 300000.0;


/*DATA*/
SELECT
    "A1"."ID"          "ID",
    "A1"."EMAIL"       "EMAIL",
    "A1"."CONTACTO1"   "CONTACTO1",
    "A1"."CONTACTO2"   "CONTACTO2",
    "A1"."DOCUMENTO"   "DOCUMENTO",
    "A1"."NOMBRES"     "NOMBRES",
    "A1"."RAZONSOCIAL" "RAZONSOCIAL"
FROM
    "MBDAA01"."DATA" "A1";
/*Incluyendome como Usuario en DATA*/
/*Intentando modificarme o borrarme en DATA*/
UPDATE MBDAA01.DATA SET NAME = 'SEBASTIAN BLANCO' WHERE ID = 10010;
DELETE FROM MBDAA01.DATA WHERE ID = 10010;
/*Otorgando permisos, esto lo deberia hacer el administrador de la base de datos*/

GRANT UPDATE, DELETE ON MBDAA01.DATA TO BD1000046615;

/*Importando los datos de DATA*/
/*Impota el ID y el correo de un usuario*/
INSERT INTO usuarios(ID,fecharegistro,correoElectronico)
SELECT ID ,TO_DATE(TO_CHAR('17/11/2022')),EMAIL
FROM MBDAA01.DATA WHERE(ID IS NOT NULL);

CREATE SEQUENCE usuario_id START WITH 1;

CREATE OR REPLACE TRIGGER TR_usuario_verificado 
BEFORE INSERT ON usuarios
FOR EACH ROW
BEGIN
    SELECT usuario_id.NEXTVAL INTO: NEW.ID FROM dual;
END;

DROP SEQUENCE usuario_id;
DROP TRIGGER TR_usuario_verificado;
/*Importa la cc de una persona de nacionalidad colombiana*/
INSERT INTO personasnaturales(ID,tipoDocumento,numeroDocumento,nombresCompleto,nacionalidad)
SELECT ID ,'CC',DOCUMENTO,NOMBRES,'Colombiana'
FROM MBDAA01.DATA WHERE (NOMBRES IS NOT NULL) AND (ID IS NOT NULL);

CREATE SEQUENCE personasNaturales_id START WITH 1;
CREATE OR REPLACE TRIGGER TR_personas_verificado 
BEFORE INSERT ON personasnaturales
FOR EACH ROW
BEGIN
    SELECT personasNaturales_id.NEXTVAL INTO: NEW.ID FROM dual;
    SELECT personasNaturales_id.NEXTVAL INTO: NEW.numeroDocumento FROM dual;
END;

DROP SEQUENCE personasNaturales_id;
DROP TRIGGER TR_personas_verificado;

/*CRUDE*/
CREATE OR REPLACE PACKAGE PC_OFERTA IS
    PROCEDURE ad (xNumero IN NUMBER, xFecha IN DATE, xDireccion IN VARCHAR, xTipoVIvienda IN VARCHAR, xTamaño IN VARCHAR, xHabitada IN NUMERIC, xCosto IN NUMERIC, xProyectoVivienda IN NUMERIC, xAnexos in VARCHAR, xEstado in VARCHAR);
    PROCEDURE mo_anexos(xNumero IN NUMBER, xAnexos IN VARCHAR);
    PROCEDURE mo_Costo(xNumero IN NUMBER, xCosto IN NUMBER);
    PROCEDURE eliminar(xNumero IN NUMBER);
    FUNCTION consultar RETURN SYS_REFCURSOR;
END;

/*CRUDI*/
CREATE OR REPLACE PACKAGE BODY PC_OFERTA IS
    PROCEDURE ad (xNumero IN NUMBER, xFecha IN DATE, xDireccion IN VARCHAR, xTipoVIvienda IN VARCHAR, xTamaño IN VARCHAR, xHabitada IN NUMERIC, xCosto IN NUMERIC, xProyectoVivienda IN NUMERIC, xAnexos in VARCHAR, xEstado in VARCHAR) 
    IS
    BEGIN
        INSERT INTO ofertas (numero,fecha,descripcion,tipovivienda,tamaño,habitada,costo,proyectovivienda,anexos,usurio,ubicacion) 
        VALUES (xNumero,xFecha,xDireccion,xTipoVIvienda,xTamaño,xHabitada,xCosto,xProyectoVivienda,xAnexos,xEstado);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20005, 'No se puede insertar la oferta.');
    END;
    --
    PROCEDURE mo_anexos (xNumero IN NUMBER, xAnexos IN VARCHAR)
    IS
    BEGIN
        UPDATE ofertas SET anexos = xAnexos WHERE numero = xNumero;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20006, 'No se puede actualizar.');
    END;
    --
    PROCEDURE mo_costo(xNumero IN NUMBER, xCosto IN NUMBER) 
    IS
    BEGIN
        UPDATE costo SET costo = xCosto WHERE numero = xNumero;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20007, 'No se puede actualizar.');
    END;
    --
    PROCEDURE eliminar(xNumero IN NUMBER)
    IS
    BEGIN
        DELETE FROM subscriptions WHERE numero = xNumero;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE_APPLICATION_ERROR(-20008, 'No se puede elimar la suscripcion.');
    END;
    --
    FUNCTION consultar RETURN SYS_REFCURSOR IS consulta SYS_REFCURSOR;
    BEGIN
	OPEN consulta FOR
		SELECT NUMERO ,
        FECHA ,
        DESCRIPCION ,
        TIPOVIVIENDA ,
        "TAMAÑO" ,
        HABITADA ,
        COSTO ,
        PROYECTOVIVIENDA ,
        ANEXOS ,
        USUARIO ,
        UBICACION  FROM ofertas;
	RETURN consulta;
	END;
    --
END;

/*CRUDOK*/
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(1,to_date('2019-DEC-20 13:35','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'sebastian@gmail.com');
INSERT INTO usuarios(ID,fecharegistro,correoElectronico) VALUES(2,to_date('2018-DEC-01 8:12','YYYY-MON-DD HH24:MI','NLS_DATE_LANGUAGE=AMERICAN'),'jose@gmail.com');

/*PC_OFERTA - ad*/
BEGIN 
    PC_OFERTA.ad(3,to_date('01-05-2022', 'dd-mm-yyyy'),'una apartamento de 1 baño','a',100,'t',10000,'f','anexos',2,'10');
END;
/*PC_OFERTA - mo_datail*/
BEGIN 
    PC_OFERTA.mo_anexos(3, 'anexosNuevos');
END;
/*PC_SUBSCRIPTIONS - mo_stage*/
BEGIN 
    PC_OFERTA.mo_costo(3, 30000);
END;
/*PC_SUBSCRIPTIONS - eliminar*/
BEGIN 
    PC_OFERTA.eliminar(1);
END;


/*ActoresE*/
CREATE OR REPLACE PACKAGE PA_USUARIO IS
    PROCEDURE ad_oferta (xID IN NUMBER, xFechaRegistro IN DATE, xCorreoElectronico IN VARCHAR);
    FUNCTION co_oferta RETURN SYS_REFCURSOR;
    PROCEDURE mo_oferta_descripcion (xNumero IN NUMBER, xDescripcion IN VARCHAR);
    PROCEDURE mo_oferta_costo (xNumero IN NUMBER, xCosto IN NUMBER);
    PROCEDURE el_oferta (xNumero IN NUMBER);
END;

CREATE OR REPLACE PACKAGE PA_ANALISTA IS
     FUNCTION co_usuarios RETURN SYS_REFCURSOR;
     FUNCTION co_viviendas RETURN SYS_REFCURSOR;
END;
-------------
/*ActoresI*/
CREATE OR REPLACE PACKAGE BODY PA_ANALISTA IS
    PROCEDURE ad_subscription (xIdSubscription IN NUMBER, xCreatedAt IN DATE, xDetail IN VARCHAR, xAccount_Id IN NUMBER)
    IS
    BEGIN
        PC_SUBSCRIPTIONS.ad(xIdSubscription, xCreatedAt, xDetail, xAccount_Id);
    END;
    --
    FUNCTION co_subscription RETURN SYS_REFCURSOR IS co_subscription SYS_REFCURSOR;
    BEGIN
        co_subscription := PC_SUBSCRIPTIONS.consultar;
        RETURN co_subscription;
	END;
    --
    PROCEDURE mo_subscription_detail (xIdSubscription IN NUMBER, xDetail IN VARCHAR)
    IS
    BEGIN
        PC_SUBSCRIPTIONS.mo_datail(xIdSubscription, xDetail);
    END;
    --
    PROCEDURE mo_subscription_stage (xIdSubscription IN NUMBER, xStatus IN VARCHAR, xPrice IN NUMBER, xEndAt IN DATE)
    IS
    BEGIN
        PC_SUBSCRIPTIONS.mo_stage(xIdSubscription, xStatus, xPrice, xEndAt);
    END;
    --
    PROCEDURE el_subscription (xIdSubscription IN NUMBER)
    IS
    BEGIN
        PC_SUBSCRIPTIONS.eliminar(xIdSubscription);
    END;
END;
/*Seguridad*/
CREATE ROLE usuario;
CREATE ROLE analista;

GRANT usuario TO bd1000046464;
GRANT analista TO bd1000046615;

GRANT INSERT, SELECT, UPDATE, DELETE ON ofertas TO usuario;
GRANT INSERT, SELECT ON usuarios TO usuario;

/*XSeguridad*/
DROP PACKAGE PA_USER;
DROP PACKAGE PA_ANALISTA;

REVOKE INSERT, SELECT, UPDATE, DELETE ON ofertas FROM usuario;
REVOKE INSERT, SELECT, UPDATE, DELETE ON usuarios FROM usuario;

DROP ROLE usuario;
DROP ROLE analista;
-------------

/*XCRUD*/
DROP PACKAGE PC_OFERTAS;


    /* XPoblar */
DELETE FROM usuarios;
DELETE FROM numeroscontactos;
DELETE FROM personasnaturales;
DELETE FROM empresas;
DELETE FROM ofertas;
DELETE FROM ofertas_fotografias;
DELETE FROM fotografias;
DELETE FROM opcionescreditos;
DELETE FROM ubicaciones;
DELETE FROM interesesen;
DELETE FROM demanda;
DELETE FROM origenesfondos

    /*XTable*/
    /*Borra todas las tablas*/

DROP TABLE EMPRESAS CASCADE CONSTRAINT PURGE;
DROP TABLE FOTOGRAFIAS CASCADE CONSTRAINT PURGE;
DROP TABLE DEMANDA CASCADE CONSTRAINT PURGE;
DROP TABLE INTERESESEN CASCADE CONSTRAINT PURGE;
DROP TABLE NUMEROSCONTACTOS CASCADE CONSTRAINT PURGE;
DROP TABLE OFERTAS CASCADE CONSTRAINT PURGE;
DROP TABLE OFERTAS_FOTOGRAFIAS CASCADE CONSTRAINT PURGE;
DROP TABLE OPCIONESCREDITOS CASCADE CONSTRAINT PURGE;
DROP TABLE ORIGENESFONDOS CASCADE CONSTRAINT PURGE;
DROP TABLE PERSONASNATURALES CASCADE CONSTRAINT PURGE;
DROP TABLE UBICACIONES CASCADE CONSTRAINT PURGE;
DROP TABLE USUARIOS CASCADE CONSTRAINT PURGE;
