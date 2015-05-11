package com.konnectcore.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Array;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

/*import sun.org.mozilla.javascript.internal.regexp.SubString;

import com.mysql.jdbc.Connection;
*/
public class StringArrayUserType implements UserType {

	private static String SPLITTER = ",";

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return String[].class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	
	
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor arg2, Object arg3) throws HibernateException,
			SQLException {
		String value = (String) StandardBasicTypes.STRING.nullSafeGet(rs, names[0], arg2);
		if(value != null)
		{
			value = value.substring(1,value.length()-1);
			if(value.equals(""))
				return org.apache.commons.lang.ArrayUtils.EMPTY_STRING_ARRAY;
		}
		
		return value != null ? value.split(SPLITTER) : org.apache.commons.lang.ArrayUtils.EMPTY_STRING_ARRAY;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor arg3) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		/*String _val = null;
		if (value != null && value instanceof String[]) {
			String[] _array = (String[]) value;
			if (_array.length != 0) {
				StringBuilder val = new StringBuilder();
				for (int i = 0; i < _array.length; i++) {
					if (StringUtils.isNotEmpty(_array[i])) {
						{
							
							val.append(_array[i]);
						}
						if (i != (_array.length - 1))
						{
							
							val.append(SPLITTER);
							
						}
					}
				}
				_val = val.toString();
			}
		}
		_val="{\""+_val+"\"}";*/
		
		java.sql.Connection con = st.getConnection();			
		String[] castObject = (String[]) value;
		
		Array array = con.createArrayOf("varchar", castObject);
		st.setArray(index, array);
		
		//StandardBasicTypes.STRING.nullSafeSet(st, _val, index, arg3);
		
	}

}


