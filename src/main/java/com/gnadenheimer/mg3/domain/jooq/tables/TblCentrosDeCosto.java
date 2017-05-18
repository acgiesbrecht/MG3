/*
 * This file is generated by jOOQ.
*/
package com.gnadenheimer.mg3.domain.jooq.tables;


import com.gnadenheimer.mg3.domain.jooq.Keys;
import com.gnadenheimer.mg3.domain.jooq.Mg;
import com.gnadenheimer.mg3.domain.jooq.tables.records.TblCentrosDeCostoRecord;

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
public class TblCentrosDeCosto extends TableImpl<TblCentrosDeCostoRecord> {

    private static final long serialVersionUID = -1237950290;

    /**
     * The reference instance of <code>MG.TBL_CENTROS_DE_COSTO</code>
     */
    public static final TblCentrosDeCosto TBL_CENTROS_DE_COSTO = new TblCentrosDeCosto();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblCentrosDeCostoRecord> getRecordType() {
        return TblCentrosDeCostoRecord.class;
    }

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.ID</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.DESCRIPCION</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, String> DESCRIPCION = createField("DESCRIPCION", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.ID_CUENTA_CONTABLE_EFECTIVO_POR_DEFECTO</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Integer> ID_CUENTA_CONTABLE_EFECTIVO_POR_DEFECTO = createField("ID_CUENTA_CONTABLE_EFECTIVO_POR_DEFECTO", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("101010100", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.ID_CUENTA_CONTABLE_CTA_CTE_POR_DEFECTO</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Integer> ID_CUENTA_CONTABLE_CTA_CTE_POR_DEFECTO = createField("ID_CUENTA_CONTABLE_CTA_CTE_POR_DEFECTO", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("101010200", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.CTA_CTE</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Integer> CTA_CTE = createField("CTA_CTE", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.PREFERIDO</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Boolean> PREFERIDO = createField("PREFERIDO", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("FALSE", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>MG.TBL_CENTROS_DE_COSTO.ES_DONACION_EXTERNA</code>.
     */
    public final TableField<TblCentrosDeCostoRecord, Boolean> ES_DONACION_EXTERNA = createField("ES_DONACION_EXTERNA", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("FALSE", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * Create a <code>MG.TBL_CENTROS_DE_COSTO</code> table reference
     */
    public TblCentrosDeCosto() {
        this("TBL_CENTROS_DE_COSTO", null);
    }

    /**
     * Create an aliased <code>MG.TBL_CENTROS_DE_COSTO</code> table reference
     */
    public TblCentrosDeCosto(String alias) {
        this(alias, TBL_CENTROS_DE_COSTO);
    }

    private TblCentrosDeCosto(String alias, Table<TblCentrosDeCostoRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblCentrosDeCosto(String alias, Table<TblCentrosDeCostoRecord> aliased, Field<?>[] parameters) {
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
    public Identity<TblCentrosDeCostoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TBL_CENTROS_DE_COSTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TblCentrosDeCostoRecord> getPrimaryKey() {
        return Keys.SQL160414103606280;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TblCentrosDeCostoRecord>> getKeys() {
        return Arrays.<UniqueKey<TblCentrosDeCostoRecord>>asList(Keys.SQL160414103606280);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TblCentrosDeCostoRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TblCentrosDeCostoRecord, ?>>asList(Keys.SQL160414103613220, Keys.SQL160414103613410);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblCentrosDeCosto as(String alias) {
        return new TblCentrosDeCosto(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblCentrosDeCosto rename(String name) {
        return new TblCentrosDeCosto(name, null);
    }
}
