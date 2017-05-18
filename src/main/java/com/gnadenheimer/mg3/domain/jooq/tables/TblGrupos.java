/*
 * This file is generated by jOOQ.
*/
package com.gnadenheimer.mg3.domain.jooq.tables;


import com.gnadenheimer.mg3.domain.jooq.Keys;
import com.gnadenheimer.mg3.domain.jooq.Mg;
import com.gnadenheimer.mg3.domain.jooq.tables.records.TblGruposRecord;

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
public class TblGrupos extends TableImpl<TblGruposRecord> {

    private static final long serialVersionUID = -362950255;

    /**
     * The reference instance of <code>MG.TBL_GRUPOS</code>
     */
    public static final TblGrupos TBL_GRUPOS = new TblGrupos();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TblGruposRecord> getRecordType() {
        return TblGruposRecord.class;
    }

    /**
     * The column <code>MG.TBL_GRUPOS.ID</code>.
     */
    public final TableField<TblGruposRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MG.TBL_GRUPOS.DESCRIPCION</code>.
     */
    public final TableField<TblGruposRecord, String> DESCRIPCION = createField("DESCRIPCION", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

    /**
     * Create a <code>MG.TBL_GRUPOS</code> table reference
     */
    public TblGrupos() {
        this("TBL_GRUPOS", null);
    }

    /**
     * Create an aliased <code>MG.TBL_GRUPOS</code> table reference
     */
    public TblGrupos(String alias) {
        this(alias, TBL_GRUPOS);
    }

    private TblGrupos(String alias, Table<TblGruposRecord> aliased) {
        this(alias, aliased, null);
    }

    private TblGrupos(String alias, Table<TblGruposRecord> aliased, Field<?>[] parameters) {
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
    public Identity<TblGruposRecord, Integer> getIdentity() {
        return Keys.IDENTITY_TBL_GRUPOS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TblGruposRecord> getPrimaryKey() {
        return Keys.SQL160209153451630;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TblGruposRecord>> getKeys() {
        return Arrays.<UniqueKey<TblGruposRecord>>asList(Keys.SQL160209153451630);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TblGrupos as(String alias) {
        return new TblGrupos(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TblGrupos rename(String name) {
        return new TblGrupos(name, null);
    }
}
