/*
 * This file is generated by jOOQ.
*/
package com.gnadenheimer.mg3.domain.jooq.tables;


import com.gnadenheimer.mg3.domain.jooq.Keys;
import com.gnadenheimer.mg3.domain.jooq.Mg;
import com.gnadenheimer.mg3.domain.jooq.tables.records.TblAsientosAsientosTemporalesRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
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
public class TblAsientosAsientosTemporales extends TableImpl<TblAsientosAsientosTemporalesRecord> {

    private static final long serialVersionUID = 1295942072;

    /**
     * The reference instance of <code>MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES</code>
     */
    public static final TblAsientosAsientosTemporales TBL_ASIENTOS_ASIENTOS_TEMPORALES = new TblAsientosAsientosTemporales();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblAsientosAsientosTemporalesRecord> getRecordType() {
        return TblAsientosAsientosTemporalesRecord.class;
    }

    /**
     * The column <code>MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES.ID_ASIENTO</code>.
     */
    public final TableField<TblAsientosAsientosTemporalesRecord, Integer> ID_ASIENTO = createField("ID_ASIENTO", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES.ID_ASIENTO_TEMPORAL</code>.
     */
    public final TableField<TblAsientosAsientosTemporalesRecord, Integer> ID_ASIENTO_TEMPORAL = createField("ID_ASIENTO_TEMPORAL", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES</code> table reference
     */
    public TblAsientosAsientosTemporales() {
        this("TBL_ASIENTOS_ASIENTOS_TEMPORALES", null);
    }

    /**
     * Create an aliased <code>MG.TBL_ASIENTOS_ASIENTOS_TEMPORALES</code> table reference
     */
    public TblAsientosAsientosTemporales(String alias) {
        this(alias, TBL_ASIENTOS_ASIENTOS_TEMPORALES);
    }

    private TblAsientosAsientosTemporales(String alias, Table<TblAsientosAsientosTemporalesRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblAsientosAsientosTemporales(String alias, Table<TblAsientosAsientosTemporalesRecord> aliased, Field<?>[] parameters) {
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
    public List<ForeignKey<TblAsientosAsientosTemporalesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TblAsientosAsientosTemporalesRecord, ?>>asList(Keys.SQL160426131315180, Keys.SQL160426131315640);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblAsientosAsientosTemporales as(String alias) {
        return new TblAsientosAsientosTemporales(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblAsientosAsientosTemporales rename(String name) {
        return new TblAsientosAsientosTemporales(name, null);
    }
}
