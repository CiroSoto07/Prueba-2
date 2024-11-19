ALTER TABLE ADMINISTRACION.TIPO_DOCUMENTO
 DROP PRIMARY KEY CASCADE;

DROP TABLE ADMINISTRACION.TIPO_DOCUMENTO CASCADE CONSTRAINTS;

CREATE TABLE ADMINISTRACION.TIPO_DOCUMENTO
(
  ID      NUMBER(18)                            NOT NULL,
  TIPO    VARCHAR2(2 BYTE)                      NOT NULL,
  NOMBRE  VARCHAR2(200 BYTE)                    NOT NULL,
  ESTADO  CHAR(1 BYTE)                          DEFAULT 'A'                   NOT NULL
)
TABLESPACE ADMINISTRACION
RESULT_CACHE (MODE DEFAULT)
PCTUSED    0
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;

COMMENT ON COLUMN ADMINISTRACION.TIPO_DOCUMENTO.ID IS 'Identificador único del registro';

COMMENT ON COLUMN ADMINISTRACION.TIPO_DOCUMENTO.TIPO IS 'Tipo de identificación asignado por el usuario';

COMMENT ON COLUMN ADMINISTRACION.TIPO_DOCUMENTO.NOMBRE IS 'Nombre del tipo de identificación';

COMMENT ON COLUMN ADMINISTRACION.TIPO_DOCUMENTO.ESTADO IS 'Estado del registro. Rango de Valores Permitidos ( A=Activo / I=Inactivo )';



CREATE UNIQUE INDEX ADMINISTRACION.IDX_TIP_IDENT1_ID ON ADMINISTRACION.TIPO_DOCUMENTO
(ID)
LOGGING
TABLESPACE ADMINISTRACION
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE UNIQUE INDEX ADMINISTRACION.IDX_TIP_IDENT1_TIPO ON ADMINISTRACION.TIPO_DOCUMENTO
(TIPO)
LOGGING
TABLESPACE ADMINISTRACION
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            NEXT             1M
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
            FLASH_CACHE      DEFAULT
            CELL_FLASH_CACHE DEFAULT
           )
NOPARALLEL;


CREATE OR REPLACE TRIGGER ADMINISTRACION.TIPO_DOCUMENTO_TRG
BEFORE INSERT
ON ADMINISTRACION.TIPO_DOCUMENTO
REFERENCING NEW AS New OLD AS Old
FOR EACH ROW
DISABLE
BEGIN
-- For Toad:  Highlight column ID
  :new.ID := TIPO_DOCUMENTO_SEQ.nextval;
END TIPO_DOCUMENTO_TRG;
/


DROP PUBLIC SYNONYM TIPO_DOCUMENTO;

CREATE OR REPLACE PUBLIC SYNONYM TIPO_DOCUMENTO FOR ADMINISTRACION.TIPO_DOCUMENTO;


ALTER TABLE ADMINISTRACION.TIPO_DOCUMENTO ADD (
  CONSTRAINT PK_TIP_DOC
  PRIMARY KEY
  (ID)
  USING INDEX ADMINISTRACION.IDX_TIP_IDENT1_ID
  ENABLE VALIDATE);

GRANT ALTER, DELETE, INDEX, INSERT, REFERENCES, SELECT, UPDATE, ON COMMIT REFRESH, QUERY REWRITE, DEBUG, FLASHBACK ON ADMINISTRACION.TIPO_DOCUMENTO TO PUBLIC;

Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (99, '99', 'SIN DATOS', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (5, '5', 'REGISTRO CIVIL', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (1, '1', 'CEDULA', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (3, '3', 'NIT', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (4, '4', 'CED. DE EXTRANJERIA.', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (6, '6', 'TARJETA IDENT.', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (7, '7', 'PASAPORTE', 'A');
Insert into ADMINISTRACION.TIPO_DOCUMENTO
   (ID, TIPO, NOMBRE, ESTADO)
 Values
   (8, '8', 'TARJETA DE EXTRANJERIA', 'A');
COMMIT;
