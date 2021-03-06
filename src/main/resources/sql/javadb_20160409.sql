CREATE TABLE MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES (
    ID_ASIENTO INTEGER NOT NULL,
    ID_ASIENTO_TEMPORAL INTEGER NOT NULL
);

ALTER TABLE MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES
    ADD FOREIGN KEY (ID_ASIENTO) REFERENCES MG.TBL_ASIENTOS (ID);

ALTER TABLE MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES
    ADD FOREIGN KEY (ID_ASIENTO_TEMPORAL) REFERENCES MG.TBL_ASIENTOS_TEMPORALES (ID);