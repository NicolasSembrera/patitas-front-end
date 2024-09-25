package pe.edu.cibertec.patitas_front_end_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.patitas_front_end_a.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_front_end_a.viewmodel.LoginModel;
import pe.edu.cibertec.patitas_front_end_a.dto.LoginRequestDTO;

@Controller
@RequestMapping("/login")

public class LoginController {
/*
    private final RestTemplate restTemplate;
    @Autowired
    public LoginController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }*/
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("00", "", "", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";
    }
    @PostMapping("/autenticar")
    public String autenticar(@RequestParam("tipoDocumento") String tipoDocumento,
                             @RequestParam("numeroDocumento") String numeroDocumento,
                             @RequestParam("password") String password,
                             Model model) {

        if (tipoDocumento == null || tipoDocumento.trim().isEmpty() ||
                numeroDocumento == null || numeroDocumento.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            LoginModel loginModel = new LoginModel("01", "Error: Debe completar correctamente sus credenciales", "","");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }

        try {
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO(tipoDocumento, numeroDocumento, password);
            String endpoint = "http://localhost:8081/autenticacion/login";
            LoginResponseDTO loginResponseDTO = restTemplate.postForObject(endpoint, loginRequestDTO, LoginResponseDTO.class);

            if (loginResponseDTO.codigo().equals("00")){
                LoginModel loginModel = new LoginModel("00", "", loginResponseDTO.nombreUsuario(),"");
                model.addAttribute("loginModel", loginModel);
                return "principal";
            }else {
                LoginModel loginModel = new LoginModel("02", "Auntenticación Fallida", "","");
                model.addAttribute("loginModel", loginModel);
                return "inicio";
            }
        }catch (Exception e){
            LoginModel loginModel = new LoginModel("99", "Error: Error desconocido", "","");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }


/*
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(tipoDocumento, numeroDocumento, password);

        ResponseEntity<LoginModel> loginModelResponse = restTemplate.postForEntity("http://localhost:8081/autenticacion/login",loginRequestDTO, LoginModel.class);

        if (!loginModelResponse.getBody().codigo().equals("00")){
            model.addAttribute("loginModel", loginModelResponse.getBody());
            return "inicio";
        }

            model.addAttribute("loginModel", loginModelResponse.getBody());
            return "principal";*/
    }


}
