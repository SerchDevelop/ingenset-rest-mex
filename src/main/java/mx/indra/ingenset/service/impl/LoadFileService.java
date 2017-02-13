package mx.indra.ingenset.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.indra.ingenset.service.ILoadFileService;
import mx.indra.ingenset.utils.SSHUtils;

@Service("serviceLoadFile")
public class LoadFileService implements ILoadFileService{
	
	static final Logger logger = Logger.getLogger(LoadFileService.class);

	public void loadFile(HttpServletRequest request, HttpServletResponse response) {

		String method = "loadFile";
		logger.info("Service :: LoadFileService :: " + method);
/*        
        File file5 = new File(request.getRealPath("privateKey"));
        System.out.println("file test :: " + file5.exists());        
  
        System.out.println("request 1 :: " + request.getContextPath());
        System.out.println("request 2 :: " + request.getPathInfo());
        System.out.println("request 3 :: " + request.getRealPath("privateKey"));
*/
        
        SSHUtils sshUtils = new SSHUtils(request, response);
        sshUtils.loadProperties();
		sshUtils.conectar2();
		sshUtils.copyFiles();
		sshUtils.desconectar();

	}
	

}
