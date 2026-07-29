"""
ngrok 隧道管理脚本（需要免费账号 https://dashboard.ngrok.com/signup 获取 authtoken）
"""
import sys
import time
import webbrowser
from pyngrok import ngrok

AUTHTOKEN = ""  # 👈 在 https://dashboard.ngrok.com/signup 注册后填入你的 authtoken
LOCAL_PORT = 8080

def main():
    if not AUTHTOKEN:
        print("ERROR: 请先在 https://dashboard.ngrok.com/signup 注册并填入 authtoken")
        print("获取 authtoken: https://dashboard.ngrok.com/get-started/your-authtoken")
        webbrowser.open("https://dashboard.ngrok.com/signup")
        sys.exit(1)

    # 配置 authtoken
    ngrok.set_auth_token(AUTHTOKEN)

    # 杀掉旧隧道
    ngrok.kill()
    time.sleep(1)

    # 创建 HTTP 隧道，指向本地 8080
    public_url = ngrok.connect(LOCAL_PORT, "http")
    print(f"✅ 隧道已建立: {public_url}")
    print("按 Ctrl+C 停止隧道")

    try:
        while True:
            time.sleep(5)
            tunnels = ngrok.get_tunnels()
            for t in tunnels:
                print(f"  隧道: {t.public_url} -> {t.config['addr']}")
    except KeyboardInterrupt:
        print("\n正在关闭隧道...")
        ngrok.kill()
        print("已关闭")

if __name__ == "__main__":
    main()
