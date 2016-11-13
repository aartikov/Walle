package com.artikov.walle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 12/11/2016
 * Time: 18:40
 *
 * @author Artur Artikov
 */
abstract public class FormValidator {
    public abstract FormValidationResult validate(Form form);
}