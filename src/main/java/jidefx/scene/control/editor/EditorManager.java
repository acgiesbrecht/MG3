/*
 * Copyright (c) 2002-2015, JIDE Software Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jidefx.scene.control.editor;

import com.jidefx.utils.CacheMap;
import javafx.geometry.BoundingBox;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jidefx.scene.control.combobox.BoundingBoxComboBox;
import jidefx.scene.control.combobox.Dimension2DComboBox;
import jidefx.scene.control.combobox.InsetsComboBox;
import jidefx.scene.control.combobox.IntegerComboBox;
import jidefx.scene.control.combobox.Point2DComboBox;
import jidefx.scene.control.combobox.Point3DComboBox;
import jidefx.scene.control.combobox.Rectangle2DComboBox;
import jidefx.scene.control.field.BoundingBoxField;
import jidefx.scene.control.field.CalendarField;
import jidefx.scene.control.field.ColorField;
import jidefx.scene.control.field.DateField;
import jidefx.scene.control.field.Dimension2DField;
import jidefx.scene.control.field.FontField;
import jidefx.scene.control.field.InsetsField;
import jidefx.scene.control.field.IntegerField;
import jidefx.scene.control.field.LocalDateField;
import jidefx.scene.control.field.LocalDateTimeField;
import jidefx.scene.control.field.LocalTimeField;
import jidefx.scene.control.field.NumberField;
import jidefx.scene.control.field.Point2DField;
import jidefx.scene.control.field.Point3DField;
import jidefx.scene.control.field.Rectangle2DField;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;

/**
 * A global object that can register editor with a type and a EditorContext.
 */
@SuppressWarnings({"UnusedDeclaration", "Convert2MethodRef"})
public class EditorManager {

    public static final String PROPERTY_EDITOR_MANAGER = "EditorManager";

    /**
     * Multi Manager Support
     */
    private static EditorManager _instance = createInstance();

    /**
     * Creates a new instance of the EditorManager.
     *
     * @return a new instance of the EditorManager.
     */
    public static EditorManager createInstance() {
        return new EditorManager();
    }

    /**
     * Gets the default instance of the EditorManager.
     *
     * @return the default instance of the EditorManager.
     */
    public static EditorManager getInstance() {
        return _instance;
    }

    /**
     * Gets the EditorManager from the node if the node has an EditorManager defined on the Properties. If not
     * there, return the default instance.
     *
     * @param node the node
     * @return an EditorManager.
     */
    public static EditorManager getInstance(Node node) {
        if (node != null && node.getProperties().get(PROPERTY_EDITOR_MANAGER) instanceof EditorManager) {
            return (EditorManager) node.getProperties().get(PROPERTY_EDITOR_MANAGER);
        }
        else {
            return getInstance();
        }
    }

    /**
     * Instance individual
     */
    private boolean _inited = false;
    private boolean _initing = false;
    private boolean _autoInit = true;

    private CacheMap<Object, EditorContext> _cache = new CacheMap<>(EditorContext.CONTEXT_DEFAULT);

    private Supplier<Editor> _defaultEditorSupplier = new Supplier<Editor>() {
        public Editor get() {
            return new TextFieldEditor();
        }
    };

    /**
     * Registers a editor with a class and a context.
     *
     * @param clazz         the type
     * @param editorFactory the editor factory
     * @param context       the editor context
     */
    public void registerEditor(Class<?> clazz, Supplier<Editor> editorFactory, EditorContext context) {
        if (clazz == null) {
            throw new IllegalArgumentException("Parameter clazz cannot be null");
        }

        if (context == null) {
            context = EditorContext.CONTEXT_DEFAULT;
        }

        if (isAutoInit() && !_inited && !_initing) {
            initDefaultEditors();
        }

        _cache.register(clazz, editorFactory, context);
    }

    /**
     * Registers an editor with a class and default context. If no context is specified, this default editor will be
     * used for that type.
     *
     * @param clazz         the type
     * @param editorFactory the editor factory
     */
    public void registerEditor(Class<?> clazz, Supplier<Editor> editorFactory) {
        registerEditor(clazz, editorFactory, EditorContext.CONTEXT_DEFAULT);
    }

    /**
     * Unregisters the editor which registers with the class and the context.
     *
     * @param clazz   the type of which the editor will be unregistered.
     * @param context the editor context.
     */
    public void unregisterEditor(Class<?> clazz, EditorContext context) {
        if (context == null) {
            context = EditorContext.CONTEXT_DEFAULT;
        }

        if (isAutoInit() && !_inited && !_initing) {
            initDefaultEditors();
        }

        _cache.unregister(clazz, context);
    }

    /**
     * Unregisters all editors which register with the class.
     *
     * @param clazz the type of which the editor will be unregistered.
     */
    public void unregisterAllEditors(Class<?> clazz) {
        _cache.remove(clazz);
    }

    /**
     * Unregisters all the editors which registered before.
     */
    public void unregisterAllEditors() {
        _cache.clear();
    }

    /**
     * Gets the registered editor.
     *
     * @param clazz   the type.
     * @param context the editor context.
     * @return the registered editor
     */
    public Editor getEditor(Class<?> clazz, EditorContext context) {
        if (isAutoInit() && !_inited && !_initing) {
            initDefaultEditors();
        }
        if (context == null) {
            context = EditorContext.CONTEXT_DEFAULT;
        }

        Editor editor;
        Object object = _cache.getRegisteredObject(clazz, context);
        if (object != null && object instanceof Supplier) {
            editor = ((Supplier<Editor>) object).get();
        }
        else if (object != null && object instanceof Editor) {
            editor = (Editor) object;
        }
        else {
            if (context.equals(EditorContext.CONTEXT_DEFAULT)) {
                editor = _defaultEditorSupplier.get();
            }
            else {
                editor = getEditor(clazz, EditorContext.CONTEXT_DEFAULT);
            }


        }

        if (editor instanceof LazyInitializeEditor) {
            ((LazyInitializeEditor) editor).initialize(clazz, context);
        }

        return editor;
    }

    /**
     * Gets the registered editor using default context.
     *
     * @param clazz the type.
     * @return the registered editor
     */
    public Editor getEditor(Class<?> clazz) {
        return getEditor(clazz, EditorContext.CONTEXT_DEFAULT);
    }

    /**
     * Checks the value of autoInit.
     *
     * @return true or false.
     * @see #setAutoInit(boolean)
     */
    public boolean isAutoInit() {
        return _autoInit;
    }

    /**
     * Sets autoInit to true or false. If autoInit is true, whenever someone tries to call methods like as getEditor,
     * {@link #initDefaultEditors()} will be called if it has never be called. By default, autoInit is true.
     * <p/>
     * This might affect the behavior if users provide their own Editors and want to overwrite default Editors.
     * In this case, instead of depending on autoInit to initialize default Editors, you should call {@link
     * #initDefaultEditors()} first, then call registerEditor to add your own Editors.
     *
     * @param autoInit true or false.
     */
    public void setAutoInit(boolean autoInit) {
        _autoInit = autoInit;
    }

    /**
     * Gets the available EditorContext registered with the class.
     *
     * @param clazz the class.
     * @return the available EditorContexts.
     */
    public EditorContext[] getEditorContexts(Class<?> clazz) {
        return _cache.getKeys(clazz, new EditorContext[0]);
    }

    /**
     * Initial the default editors.
     */
    public void initDefaultEditors() {
        if (_inited) {
            return;
        }
        _initing = true;

        try {
            registerEditor(Color.class, () -> new ColorField());
            registerEditor(Color.class, () -> new ColorField(ColorField.ColorFormat.RGB), new EditorContext(ColorField.ColorFormat.RGB.name()));
            registerEditor(Color.class, () -> new ColorField(ColorField.ColorFormat.HEX_RGB), new EditorContext(ColorField.ColorFormat.HEX_RGB.name()));
            registerEditor(Color.class, () -> new ColorField(ColorField.ColorFormat.RGBA), new EditorContext(ColorField.ColorFormat.RGBA.name()));
            registerEditor(Color.class, () -> new ColorField(ColorField.ColorFormat.HEX_RGBA), new EditorContext(ColorField.ColorFormat.HEX_RGBA.name()));

            registerEditor(Integer.class, () -> new IntegerField());
            registerEditor(Integer.class, () -> new IntegerComboBox(), new EditorContext("ComboBox"));

            registerEditor(Double.class, () -> new NumberField(NumberField.NumberType.Currency), new EditorContext("Currency"));
            registerEditor(Double.class, () -> new NumberField(NumberField.NumberType.Percent), new EditorContext("Percent"));

            registerEditor(Date.class, () -> new DateField());
            registerEditor(Calendar.class, () -> new CalendarField());
            registerEditor(LocalDate.class, () -> new LocalDateField());
            registerEditor(LocalDateTime.class, () -> new LocalDateTimeField());
            registerEditor(LocalTime.class, () -> new LocalTimeField());

            registerEditor(Enum.class, () -> new EnumChoiceBoxEditor());
            registerEditor(Enum.class, () -> new EnumComboBoxEditor(), new EditorContext("ComboBox"));

            registerEditor(Boolean.class, () -> new CheckBoxEditor());

            registerEditor(Point2D.class, () -> new Point2DField());
            registerEditor(Point2D.class, () -> new Point2DComboBox(), new EditorContext("ComboBox"));

            registerEditor(Point3D.class, () -> new Point3DField());
            registerEditor(Point3D.class, () -> new Point3DComboBox(), new EditorContext("ComboBox"));

            registerEditor(Dimension2D.class, () -> new Dimension2DField());
            registerEditor(Dimension2D.class, () -> new Dimension2DComboBox(), new EditorContext("ComboBox"));

            registerEditor(BoundingBox.class, () -> new BoundingBoxField());
            registerEditor(BoundingBox.class, () -> new BoundingBoxComboBox(), new EditorContext("ComboBox"));

            registerEditor(Insets.class, () -> new InsetsField());
            registerEditor(Insets.class, () -> new InsetsComboBox(), new EditorContext("ComboBox"));

            registerEditor(Rectangle2D.class, () -> new Rectangle2DField());
            registerEditor(Rectangle2D.class, () -> new Rectangle2DComboBox(), new EditorContext("ComboBox"));

            registerEditor(Font.class, () -> new FontField());
        }
        finally {
            _initing = false;
            _inited = true;
        }
    }

    /**
     * If {@link #initDefaultEditors()} is called once, calling it again will have no effect because an internal flag is
     * set. This method will reset the internal flag so that you can call {@link #initDefaultEditors()} in case you
     * unregister all editors using {@link #unregisterAllEditors()}.
     */
    public void resetInit() {
        _inited = false;
    }

    public void clear() {
        resetInit();
        _cache.clear();
    }
}
