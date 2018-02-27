package org.javabrains.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.type.Type;

public class CustomIdGenerator implements IdentifierGenerator, Configurable {
	
	private Properties params;
	
	@Override
	public void configure(Type type, Properties params, Dialect dialect)
			throws MappingException {
		this.params = params;
	}

	@Override
	public Serializable generate(SessionImplementor session, Object obj)
			throws HibernateException {
		
		final String sequencePerEntitySuffix = ConfigurationHelper.getString(SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, params, SequenceStyleGenerator.DEF_SEQUENCE_SUFFIX);

       /* final String defaultSequenceName = ConfigurationHelper.getBoolean(SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, params, true)
                ? sequencePerEntitySuffix : SequenceStyleGenerator.DEF_SEQUENCE_NAME;*/

        String seqParam = ConfigurationHelper.getString(SequenceStyleGenerator.SEQUENCE_PARAM, params, sequencePerEntitySuffix);
        String query = "select next_val from id_sequences where sequence_name='"+seqParam+"';";
		try {
			Connection conn = session.connection();
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int id = rs.getInt(1);
				String code  = sequencePerEntitySuffix +"_"+id;
				int updateId=id+1;
				String updateQuery="UPDATE id_sequences SET next_val= '"+updateId+"' WHERE sequence_name='"+seqParam+"'";
				PreparedStatement updatePS=conn.prepareStatement(updateQuery);
				updatePS.executeUpdate();
				return code;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*String prefix = "user_";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select max(user_id) as Id from sample.user_details");

            if(rs.next())
            {
                int id=rs.getInt(1)+1;
                String generatedId = prefix + new Integer(id).toString();
                System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


        return null;
    }

	

}
