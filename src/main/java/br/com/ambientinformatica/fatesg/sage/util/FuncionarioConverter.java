package br.com.ambientinformatica.fatesg.sage.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.sage.entidade.Contato;
import br.com.ambientinformatica.fatesg.sage.persistencia.ContatoDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("contatoConverter")
public class FuncionarioConverter implements Converter {  

   private ContatoDao contatoDao = (ContatoDao)FabricaAbstrata.criarObjeto("contatoDao");
   
   @Override
   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
       if (value == null || value.equals("")) {  
           return "";  
       } else {  
           return String.valueOf(((Contato) value).getId());  
       }  
   }


   @Override
   public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if (value != null && !value.trim().equals("")) {  
         Contato contato = new Contato();
         try {  
            int id = Integer.parseInt(value);  

            try {
               contato = contatoDao.consultar(id);
            } catch (PersistenciaException e) {
               e.printStackTrace();
            }
         } catch(NumberFormatException exception) {  
//            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Funcionario escolhido não é válido"));
            return null;
         }  
         return contato;  
      }else{
         return null;
      }
   }
}  

