package cn.gloryroad;

import java.io.File;
import java.io.IOException;


/**
 * @author Administrator
 *FileUtil���ڴ���Ŀ¼���ļ�����������ֻʹ�ô��ഴ��Ŀ¼�ķ����������ļ��ķ������Բο��Ժ���Ը���
 *������Ҫ����ָ���������ļ�
 */
public class FileUtil {
	public static boolean createFlie(String destFileName){
		File file = new File(destFileName);
		if (file.exists()){
			System.out.println("���������ļ�" + destFileName + "ʧ�ܣ� Ŀ���ļ��Ѵ���");
			return false;
		}
		if (destFileName.endsWith(File.separator)){
			System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ�겻��ΪĿ¼");
			return false;
		}
		//�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����
		if (!file.getParentFile().exists()){
			System.out.println("Ŀ��Ŀ¼�����ڣ�׼������");
			if(!file.getParentFile().mkdirs()){
				System.out.println("����Ŀ���ļ�����Ŀ¼ʧ��");
				return false;
			}
		}
		
		//����Ŀ���ļ�
		try {
			if (file.createNewFile()){
				System.out.println("���������ļ�" + destFileName + "�ɹ�");
				return true;
			}else {
				System.out.println("���������ļ�" + destFileName + "ʧ��");
				return false;
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("���������ļ�" + destFileName + "ʧ��! " + e.getMessage());
			return false;
		}
	}
	
	public static boolean createDir(String destDirName){
		File dir = new File(destDirName);
		if (dir.exists()){
			System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ� Ŀ��Ŀ¼�Ѵ���");
			return false;
		}
		
		//����Ŀ¼
		if (dir.mkdirs()){
			System.out.println("����Ŀ¼" + destDirName + "�ɹ�");
			return true;
		}else {
			System.err.println("����Ŀ¼" + destDirName + "ʧ��");
			return false;
		}
	}
}
