cd /d "C:\Program Files\MySQL\MySQL Server 9.7\bin"
mysql -u root -proot people_techno --default-character-set=utf8mb4 < "G:\JavaWorkSpace\people-techno-service\sql\seed_phones.sql"
echo Import finished, phones: !ERRORLEVEL!
mysql -u root -proot people_techno --default-character-set=utf8mb4 -e "SELECT COUNT(*) AS total FROM phone; SELECT COUNT(*) AS basics FROM phone_basic;"
