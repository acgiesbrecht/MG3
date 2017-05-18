/*
 * This file is generated by jOOQ.
*/
package com.gnadenheimer.mg3.domain.jooq.tables;


import com.gnadenheimer.mg3.domain.jooq.Keys;
import com.gnadenheimer.mg3.domain.jooq.Mg;
import com.gnadenheimer.mg3.domain.jooq.tables.records.TblEntidadesRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TblEntidades extends TableImpl<TblEntidadesRecord> {

    private static final long serialVersionUID = 1499199754;

    /**
     * The reference instance of <code>MG.TBL_ENTIDADES</code>
     */
    public static final TblEntidades TBL_ENTIDADES = new TblEntidades();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblEntidadesRecord> getRecordType() {
        return TblEntidadesRecord.class;
    }

    /**
     * The column <code>MG.TBL_ENTIDADES.ID</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.NOMBRES</code>.
     */
    public final TableField<TblEntidadesRecord, String> NOMBRES = createField("NOMBRES", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.APELLIDOS</code>.
     */
    public final TableField<TblEntidadesRecord, String> APELLIDOS = createField("APELLIDOS", org.jooq.impl.SQLDataType.VARCHAR.length(128).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.RAZON_SOCIAL</code>.
     */
    public final TableField<TblEntidadesRecord, String> RAZON_SOCIAL = createField("RAZON_SOCIAL", org.jooq.impl.SQLDataType.VARCHAR.length(256).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.RUC_SIN_DV</code>.
     */
    public final TableField<TblEntidadesRecord, String> RUC_SIN_DV = createField("RUC_SIN_DV", org.jooq.impl.SQLDataType.VARCHAR.length(20).nullable(false).defaultValue(org.jooq.impl.DSL.field("'44444401'", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.CTACTE</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> CTACTE = createField("CTACTE", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("99999", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.DOMICILIO</code>.
     */
    public final TableField<TblEntidadesRecord, String> DOMICILIO = createField("DOMICILIO", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.BOX</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> BOX = createField("BOX", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.IS_MIEMBRO_ACTIVO</code>.
     */
    public final TableField<TblEntidadesRecord, Boolean> IS_MIEMBRO_ACTIVO = createField("IS_MIEMBRO_ACTIVO", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("FALSE", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_FORMA_DE_PAGO_PREFERIDA</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_FORMA_DE_PAGO_PREFERIDA = createField("ID_FORMA_DE_PAGO_PREFERIDA", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.APORTE_MENSUAL</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> APORTE_MENSUAL = createField("APORTE_MENSUAL", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_ENTIDAD_PAGANTE_APORTES</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_ENTIDAD_PAGANTE_APORTES = createField("ID_ENTIDAD_PAGANTE_APORTES", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.FECHA_NACIMIENTO</code>.
     */
    public final TableField<TblEntidadesRecord, Timestamp> FECHA_NACIMIENTO = createField("FECHA_NACIMIENTO", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.FECHA_BAUTISMO</code>.
     */
    public final TableField<TblEntidadesRecord, Timestamp> FECHA_BAUTISMO = createField("FECHA_BAUTISMO", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.FECHA_ENTRADA_CONGREGACION</code>.
     */
    public final TableField<TblEntidadesRecord, Timestamp> FECHA_ENTRADA_CONGREGACION = createField("FECHA_ENTRADA_CONGREGACION", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.FECHA_SALIDA_CONGREGACION</code>.
     */
    public final TableField<TblEntidadesRecord, Timestamp> FECHA_SALIDA_CONGREGACION = createField("FECHA_SALIDA_CONGREGACION", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.FECHA_DEFUNCION</code>.
     */
    public final TableField<TblEntidadesRecord, Timestamp> FECHA_DEFUNCION = createField("FECHA_DEFUNCION", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_AREA_SERVICIO_EN_IGLESIA</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_AREA_SERVICIO_EN_IGLESIA = createField("ID_AREA_SERVICIO_EN_IGLESIA", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_MIEMBROS_CATEGORIA_DE_PAGO</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_MIEMBROS_CATEGORIA_DE_PAGO = createField("ID_MIEMBROS_CATEGORIA_DE_PAGO", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_MIEMBROS_ALERGIA</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_MIEMBROS_ALERGIA = createField("ID_MIEMBROS_ALERGIA", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.ID_USER</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> ID_USER = createField("ID_USER", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.APORTE_SALDO_ANTERIOR</code>.
     */
    public final TableField<TblEntidadesRecord, Long> APORTE_SALDO_ANTERIOR = createField("APORTE_SALDO_ANTERIOR", org.jooq.impl.SQLDataType.BIGINT.defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.MES_INICIO_APORTE</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> MES_INICIO_APORTE = createField("MES_INICIO_APORTE", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("2", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.MES_FIN_APORTE</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> MES_FIN_APORTE = createField("MES_FIN_APORTE", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("11", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_ENTIDADES.CANTIDAD_DE_DEPENDIENTES_APORTANTES</code>.
     */
    public final TableField<TblEntidadesRecord, Integer> CANTIDAD_DE_DEPENDIENTES_APORTANTES = createField("CANTIDAD_DE_DEPENDIENTES_APORTANTES", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>MG.TBL_ENTIDADES</code> table reference
     */
    public TblEntidades() {
        this("TBL_ENTIDADES", null);
    }

    /**
     * Create an aliased <code>MG.TBL_ENTIDADES</code> table reference
     */
    public TblEntidades(String alias) {
        this(alias, TBL_ENTIDADES);
    }

    private TblEntidades(String alias, Table<TblEntidadesRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblEntidades(String alias, Table<TblEntidadesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Mg.MG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TblEntidadesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TBL_ENTIDADES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TblEntidadesRecord> getPrimaryKey() {
        return Keys.SQL160209153453130;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TblEntidadesRecord>> getKeys() {
        return Arrays.<UniqueKey<TblEntidadesRecord>>asList(Keys.SQL160209153453130);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TblEntidadesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TblEntidadesRecord, ?>>asList(Keys.SQL160209153459490, Keys.SQL160209153459620, Keys.SQL160209153459770, Keys.SQL160209153459930, Keys.SQL160209153500090, Keys.SQL160209153500250);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblEntidades as(String alias) {
        return new TblEntidades(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblEntidades rename(String name) {
        return new TblEntidades(name, null);
    }
}
