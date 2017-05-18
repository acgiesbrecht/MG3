/*
 * This file is generated by jOOQ.
*/
package com.gnadenheimer.mg3.domain.jooq.tables;


import com.gnadenheimer.mg3.domain.jooq.Keys;
import com.gnadenheimer.mg3.domain.jooq.Mg;
import com.gnadenheimer.mg3.domain.jooq.tables.records.TblMiembrosCategoriasDePagoRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class TblMiembrosCategoriasDePago extends TableImpl<TblMiembrosCategoriasDePagoRecord> {

    private static final long serialVersionUID = -9276986;

    /**
     * The reference instance of <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO</code>
     */
    public static final TblMiembrosCategoriasDePago TBL_MIEMBROS_CATEGORIAS_DE_PAGO = new TblMiembrosCategoriasDePago();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblMiembrosCategoriasDePagoRecord> getRecordType() {
        return TblMiembrosCategoriasDePagoRecord.class;
    }

    /**
     * The column <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO.ID</code>.
     */
    public final TableField<TblMiembrosCategoriasDePagoRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO.DESCRIPCION</code>.
     */
    public final TableField<TblMiembrosCategoriasDePagoRecord, String> DESCRIPCION = createField("DESCRIPCION", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * The column <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO.ES_ACTIVACION</code>.
     */
    public final TableField<TblMiembrosCategoriasDePagoRecord, Boolean> ES_ACTIVACION = createField("ES_ACTIVACION", org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * Create a <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO</code> table reference
     */
    public TblMiembrosCategoriasDePago() {
        this("TBL_MIEMBROS_CATEGORIAS_DE_PAGO", null);
    }

    /**
     * Create an aliased <code>MG.TBL_MIEMBROS_CATEGORIAS_DE_PAGO</code> table reference
     */
    public TblMiembrosCategoriasDePago(String alias) {
        this(alias, TBL_MIEMBROS_CATEGORIAS_DE_PAGO);
    }

    private TblMiembrosCategoriasDePago(String alias, Table<TblMiembrosCategoriasDePagoRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblMiembrosCategoriasDePago(String alias, Table<TblMiembrosCategoriasDePagoRecord> aliased, Field<?>[] parameters) {
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
    public Identity<TblMiembrosCategoriasDePagoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TBL_MIEMBROS_CATEGORIAS_DE_PAGO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TblMiembrosCategoriasDePagoRecord> getPrimaryKey() {
        return Keys.SQL160209153453780;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TblMiembrosCategoriasDePagoRecord>> getKeys() {
        return Arrays.<UniqueKey<TblMiembrosCategoriasDePagoRecord>>asList(Keys.SQL160209153453780);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblMiembrosCategoriasDePago as(String alias) {
        return new TblMiembrosCategoriasDePago(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblMiembrosCategoriasDePago rename(String name) {
        return new TblMiembrosCategoriasDePago(name, null);
    }
}
