package conexao;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
	DB BaseDados;
	DBCollection colecao;
	BasicDBObject document = new BasicDBObject();
	
	public Connection() {
		try {
			
			Mongo m = new Mongo("localhost",27017);
			
			BaseDados = m.getDB("teste");
			
			colecao = BaseDados.getCollection("pessoas");
			
			System.out.println("Conexão efetuada com sucesso!");
			
		} catch (Exception e) {
			Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null,e);
		}
	}
	
	public boolean inserir (String Dado) {
		document.put("RG",Dado);
		colecao.insert(document);
		return true;
	}
	
	public void mostrar() {
		DBCursor cursor = colecao.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	
	public boolean atualizar(String DadoAntigo, String DadoNovo) {
		document.put("RG",DadoAntigo);
		BasicDBObject DocNovo = new BasicDBObject();
		DocNovo.put("RG", DadoNovo);
		colecao.findAndModify(document, DocNovo);
		return true;
	}
	
	public boolean remover(String Dado) {
		document.put("RG", Dado);
		colecao.remove(document);
		return true;
	}
	
}
