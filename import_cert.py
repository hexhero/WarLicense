import zipfile

def sendCert(warpath):
    with zipfile.ZipFile(warpath,'a') as war:
        war.write("WEB-INF/classes/cert.cer")

if __name__ == '__main__':
    sendCert(input("请输入war包名称"))