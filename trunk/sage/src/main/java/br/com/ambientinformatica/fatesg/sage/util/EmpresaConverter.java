package br.com.ambientinformatica.fatesg.sage.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.ambientinformatica.fatesg.sage.entidade.Estagio;
import br.com.ambientinformatica.fatesg.sage.persistencia.EstagioDao;
import br.com.ambientinformatica.jpa.exception.PersistenciaException;
import br.com.ambientinformatica.jpa.util.FabricaAbstrata;

@FacesConverter("empresaConverter")
public class EmpresaConverter implements Converter {
	
	private EstagioDao estagioDao = (EstagioDao) FabricaAbstrata.criarObjeto("estagioDao");

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Estagio) value).getId());
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.trim().equals("")) {
			Estagio estagio = new Estagio();
			try {
				int id = Integer.parseInt(value);

				try {
					estagio = estagioDao.consultar(id);
				} catch (PersistenciaException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException exception) {
				// throw new ConverterException(new
				// FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
				// "Message"));
				return null;
			}
			return estagio;
		} else {
			return null;
		}
	}
}
