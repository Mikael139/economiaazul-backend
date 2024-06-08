package br.com.globalsolution.economiaazul.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.globalsolution.economiaazul.model.DenunciaAnonimaModel;
import br.com.globalsolution.economiaazul.service.DenunciaAnonimaService;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class DenunciaAnonimaControle {

    @Autowired
    private DenunciaAnonimaService das;

    private static final long MAX_IMAGE_SIZE = 16 * 1024 * 1024; // 16 MB

    @PostMapping("/cadastrarDenuncias")
    public ResponseEntity<?> cadastrar(@RequestParam("datadadenuncia") String datadadenuncia,
                                       @RequestParam("horarioDenuncia") String horarioDenuncia,
                                       @RequestParam("observacao") String observacao,
                                       @RequestParam("imagem") MultipartFile imagem) {
        try {
            long imageSize = imagem.getSize();
            System.out.println("Tamanho da imagem: " + imageSize + " bytes");
            
            if (imageSize > MAX_IMAGE_SIZE) {
                return ResponseEntity.status(400).body("Imagem é muito grande. O tamanho máximo permitido é 16MB.");
            }
            
            DenunciaAnonimaModel denunciaAnonimaModel = new DenunciaAnonimaModel();
            denunciaAnonimaModel.setDatadadenuncia(datadadenuncia);
            denunciaAnonimaModel.setHorarioDenuncia(horarioDenuncia);
            denunciaAnonimaModel.setObservacao(observacao);
            denunciaAnonimaModel.setImagem(imagem.getBytes());

            return das.cadastrarAlterar(denunciaAnonimaModel, "cadastrar");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao processar a imagem");
        }
    }

    @GetMapping("/listarDenuncias")
    public Iterable<DenunciaAnonimaModel> listar() {
        return das.listar();
    }

    @GetMapping("/")
    public String rota() {
        return "API funcionando";
    }

    @GetMapping("/verImagem/{id}")
    public ResponseEntity<byte[]> verImagem(@PathVariable Long id) {
        DenunciaAnonimaModel denunciaAnonima = das.obterPorId(id);
        if (denunciaAnonima != null && denunciaAnonima.getImagem() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(denunciaAnonima.getImagem(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}