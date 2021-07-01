package BeutifulSalon.dao;
/**
 *
 * @author Melissa
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Produto;
import java.sql.SQLException;
import java.sql.Date;

public class produtoDAO {
    public void cadastrarProduto(Produto produto) throws ExceptionDAO{
        String sql = "insert into produto(nome, marca, preco, dataValidade) value (?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
  
            pStatement.setString(1, produto.getNome());
            pStatement.setString(2, produto.getMarca());
            pStatement.setLong(3, produto.getPreco());
            pStatement.setDate(4, (Date) produto.getDataValidade());
            boolean execute = pStatement.execute();
        } catch(SQLException e){
            throw new ExceptionDAO("Erro ao cadastrar o produto: "+ e);
        } finally{
            try{
                if(pStatement != null){
                    pStatement.close();
                }
            } catch(SQLException e){
                throw new ExceptionDAO("Erro ao fechar o Statement "+ e);
            }try{
                if(connection!= null){
                    connection.close();
                }
            }catch(SQLException e){
                throw new ExceptionDAO("Erro ao fazer a conexão "+ e);
            }
        }
    }
    
}
