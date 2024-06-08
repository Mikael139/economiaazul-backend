package br.com.globalsolution.economiaazul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.globalsolution.economiaazul.model.DenunciaAnonimaModel;
import br.com.globalsolution.economiaazul.model.RespostaModel;
import br.com.globalsolution.economiaazul.repositorio.DenunciaAnonimaRepositorio;

import java.util.Optional;

@Service
public class DenunciaAnonimaService {

    @Autowired
    private DenunciaAnonimaRepositorio dar;

    @Autowired
    private RespostaModel dam;

    // Método para listar todas as denúncias
    public Iterable<DenunciaAnonimaModel> listar() {
        return dar.findAll();
    }

    // Método para cadastrar ou alterar uma denúncia
    public ResponseEntity<?> cadastrarAlterar(DenunciaAnonimaModel dm, String acao) {

        if (dm.getDatadadenuncia().equals("")) {
            dam.setMensagem("O campo Data da Denuncia é obrigatório!");
            return new ResponseEntity<RespostaModel>(dam, HttpStatus.BAD_REQUEST);
        } else if (dm.getHorarioDenuncia().equals("")) {
            dam.setMensagem("Horário da denuncia é obrigatório!");
            return new ResponseEntity<RespostaModel>(dam, HttpStatus.BAD_REQUEST);
        } else if (dm.getObservacao().equals("")) {
            dam.setMensagem("O campo de observação é obrigatório!");
            return new ResponseEntity<RespostaModel>(dam, HttpStatus.BAD_REQUEST);
        } else {
            if (acao.equals("cadastrar")) {
                // Se a opção for cadastrar será CREATED
                return new ResponseEntity<DenunciaAnonimaModel>(dar.save(dm), HttpStatus.CREATED);
            } else {
                // Se a opção for Alterar será OK
                return new ResponseEntity<DenunciaAnonimaModel>(dar.save(dm), HttpStatus.valueOf(0));
            }
        }
    }

    // Método para obter uma denúncia por ID
    public DenunciaAnonimaModel obterPorId(Long id) {
        Optional<DenunciaAnonimaModel> denuncia = dar.findById(id);
        return denuncia.orElse(null);
    }
}
