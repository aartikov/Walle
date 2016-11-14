package com.artikov.walle_sample;

import com.artikov.walle.Field;
import com.artikov.walle.Form;

/**
 * Date: 12/11/2016
 * Time: 15:57
 *
 * @author Artur Artikov
 */
public class SignUpForm extends Form {
	public static Field<String> EMAIL = new Field<>("EMAIL");
	public static Field<String> PASSWORD = new Field<>("PASSWORD");
	public static Field<String> CONFIRM_PASSWORD = new Field<>("CONFIRM_PASSWORD");
}
