package Nash.example.register.controller;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class FileController {
    /*@GetMapping("/upload")
    public String doUpload(){
        return "upload";
    }*/
    @GetMapping("/upload")
    public String doUpload(Model model){
        // 将上传文件目录下的所有文件名列出来，保存到模型对象中
        String uploadPath = "C:" + File.separator + "SpringBootUpload";
        File dir = new File(uploadPath);
        model.addAttribute("files", dir.list());
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request){
        // 得到所有文件的列表
        List<MultipartFile> files =
                ((MultipartHttpServletRequest) request).getFiles("file");
        String uploadPath = "C:" + File.separator + "SpringBootUpload";
        File dir = new File(uploadPath);
        // 如果保存上传文件的目录不存在，则创建它
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile f : files) {
            if (f.isEmpty()) {
                continue;
            }
            File target = new File(uploadPath + File.separator + f.getOriginalFilename());
            try {
                f.transferTo(target);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "文件上传成功！";
    }

    /*@GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam String fileName) throws IOException{
        String dir = "F:" + File.separator + "SpringBootUpload";
        String fileFullPath = dir + File.separator + fileName;
        File file = new File(fileFullPath);
        try(
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
        ){
            response.addHeader("Content-Type","application/octet-stream");
            response.addHeader("Content-Disposition", "attatchment; fileName="
                    + new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
            response.addHeader("Content-Length", Long.toString(file.length()));
            try(
                    OutputStream os = response.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
            ){
                byte[] buf = new byte[1024];
                int len = 0;
                while((len = bis.read(buf)) != -1){
                    bos.write(buf);
                }
            }
        }
    }*/

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName) throws IOException {
        String dir = "C:" + File.separator + "SpringBootUpload";
        String fileFullPath = dir + File.separator + fileName;
        File file = new File(fileFullPath);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentLength(file.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8),
                StandardCharsets.ISO_8859_1);
        builder.header("Content-Disposition", "attachment; filename=" + fileName);
        return builder.body(FileUtils.readFileToByteArray(file));
    }
}