package net.shop.util;

import net.shop.error.*;
import net.shop.vo.EmailVO;
import net.shop.vo.PagingVO;
import net.shop.vo.ReplyVO;

import net.shop.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


@Component("util")
public class Util {

	@Autowired
    protected JavaMailSender  mailSender;

    public PagingVO paging(int requestPage, int countPerPage, int totalCount) {

        final int PAGING_PAGE = 10;
        int totalPageCount = 0;
        int firstRow = 0;
        int endRow = 0;
        int beginPage = 0;
        int endPage = 0;

        if(totalCount != 0 || requestPage < 0) {

            totalPageCount = totalCount / countPerPage;         // pageCount : 3
            if(totalCount % countPerPage > 0) {
                totalPageCount++;                               // pageCount : 4
            }

            firstRow = (requestPage - 1) * countPerPage + 1;
            endRow = firstRow + countPerPage - 1;

            if(endRow > totalCount){
                endRow = totalCount;
            }

            if(totalCount != 0) {
                beginPage = (requestPage - 1) / PAGING_PAGE * PAGING_PAGE + 1;
                endPage = beginPage + PAGING_PAGE - 1;
                if(endPage > totalPageCount){
                    endPage = totalPageCount;
                }
            }
        } else {
            requestPage = 0;
        }

        PagingVO pagingVO = new PagingVO(requestPage, totalPageCount, firstRow, endRow, beginPage, endPage);

        return pagingVO;
    }

    public void isMemberId(String memberId) throws MemberIdNotFoundException{
        if(memberId == null || memberId.equals("")){
            throw new MemberIdNotFoundException("not login in.");
        }
    }

    public String isMemberId(Authentication auth) throws MemberIdNotFoundException{

        UserDetails vo = (UserDetails) auth.getPrincipal();
        String memberId = vo.getUsername();

        if(memberId == null || memberId.equals("")){
            throw new MemberIdNotFoundException("not login in.");
        }

        return memberId;
    }

    public void isEqualMemberId(String email, String memberId) throws MemberIdNotEqualsException{
        if (!email.equals(memberId)) {
            throw new MemberIdNotEqualsException("작성자의 ID와 로그인한 사용자의 ID가 다릅니다.");
        }
    }

    public EmailVO getRegisterConfirmEmailVO(UserVO userVO, String url) {
        EmailVO emailVO = new EmailVO();
        long stamp = System.currentTimeMillis() + 3600000;
        String subject = "No Reply - Doora";
        String path = url + "/user/emailConfirm.do?id="+userVO.getNumber()+"&email="
                +userVO.getEmail()+"&pwd="+userVO.getPassword()+"&expire=" + stamp;
        String content = "User:" + userVO.getEmail() + " confirm register: " + path;
        System.err.println("confirm url:" + path);
        emailVO.setReciver(userVO.getEmail());
        emailVO.setSubject(subject);
        emailVO.setContent(content);
        return emailVO;
    }

    public void checkParent(ReplyVO parent, int parentNumber) throws NotFoundException, CannotReplyException {
        if(parent == null){
            throw new NotFoundException("부모글이 존재하지 않음 : " + parentNumber);
        }

        int parentLevel = parent.getLevel();
        if(parentLevel == 1){
            throw new CannotReplyException("마지막 레벨 글에는 답글을 달 수 없습니다 : " + parent.getNumber());
        }
    }

    public String getSearchMinSeqNum(ReplyVO parent){
        String parentSeqNum = parent.getSequenceNumber();
        DecimalFormat decimalFormat = new DecimalFormat("000000000000");
        long parentSeqLongValue = Long.parseLong(parentSeqNum);
        long searchMinLongValue = 0;
        switch (parent.getLevel()){                                             // 0000009999 21
            case 0:
                searchMinLongValue = parentSeqLongValue / 100L * 100L;          // 0000009999 00
                break;
        }
        return decimalFormat.format(searchMinLongValue);
    }

    public String getSequenceNumber(ReplyVO parent, String lastChildSeq) throws LastChildAleadyExistsException {
        long parentSeqLong = Long.parseLong(parent.getSequenceNumber());
        int parentLevel = parent.getLevel();

        long decUnit = 0;
        if(parentLevel == 0){
            decUnit = 1L;
        }

        String sequenceNumber = null;

        DecimalFormat decimalFormat = new DecimalFormat("000000000000");
        if(lastChildSeq == null){   // 답변글이 없음
            sequenceNumber = decimalFormat.format(parentSeqLong - decUnit);
        } else {

            String orderOfLastChildSeq = null;
            if(parentLevel == 0){
                orderOfLastChildSeq = lastChildSeq.substring(10, 12);       // 0000000000 00
                sequenceNumber = lastChildSeq;
            }
            if(orderOfLastChildSeq.equals("00")){
                throw new LastChildAleadyExistsException("마지막 자식글이 이미 존재합니다 : " + lastChildSeq);
            }
            long seq = Long.parseLong(sequenceNumber) - decUnit;
            sequenceNumber = decimalFormat.format(seq);
        }
        return sequenceNumber;
    }

    public static void fileUpload(MultipartFile multipartFile, String path, String fileName) throws IOException {
    	 //String originalFileName = multipartFile.getOriginalFilename();
    	 //String contentType = multipartFile.getContentType();
    	 long fileSize = multipartFile.getSize();
    	 InputStream is = null;
    	 OutputStream out = null;
    	 
    	 try{
    		 if (fileSize > 0) {
    			 is = multipartFile.getInputStream();
    			 File realUploadDir = new File(path);
    			 if (!realUploadDir.exists()) {     //if doesn't exist file in the path, make it
    				 realUploadDir.mkdirs();
    			 }
    			 out = new FileOutputStream(path +"/"+ fileName);
    			 FileCopyUtils.copy(is, out);       //copy file of InputStream to outputStream
    		 }else{
    			 new IOException("Wrong File");
    		 }
    	 }catch(IOException e){
    		 e.printStackTrace();
    		 new IOException("Upload failed");
    	 }finally{
    		 if(out != null){out.close();}
    		 if(is != null){is.close();}
    	 }
    }
    
    public static void editorUpload(MultipartFile multipartFile, String path, String fileName) throws IOException {
        
		Long size = 0L;
        File file = new File(path);
       
        //make directory if it isn't
        if(!file.exists()) {
            file.mkdirs();
        }
        //change upload filename
        size = multipartFile.getSize();
        //write in server
        InputStream is = multipartFile.getInputStream();
        OutputStream os=new FileOutputStream(path + fileName);
        int numRead;
        byte b[] = new byte[(int)multipartFile.getSize()];
        while((numRead = is.read(b,0,b.length)) != -1){
            os.write(b,0,numRead);
        }
        if(is != null)  is.close();
        os.flush();
        os.close();
    }
    
    //send email
    public void sendEmail(EmailVO emailVO) throws Exception {
        
        MimeMessage msg = mailSender.createMimeMessage();
        msg.setSubject(emailVO.getSubject());
        msg.setText(emailVO.getContent());
        msg.setRecipient(RecipientType.TO , new InternetAddress(emailVO.getReciver()));
         
        mailSender.send(msg); 
    }
}
