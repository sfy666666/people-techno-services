$ErrorActionPreference = "Continue"

# Write JSON bodies to temp files to avoid escaping issues
$bodyDir = "$env:TEMP\pt_test"
New-Item -ItemType Directory -Path $bodyDir -Force | Out-Null

# Register body
@{"username"="testuser02";"password"="123456"} | ConvertTo-Json -Compress | Out-File -FilePath "$bodyDir\register.json" -Encoding UTF8

# Login body
@{"username"="testuser02";"password"="123456"} | ConvertTo-Json -Compress | Out-File -FilePath "$bodyDir\login.json" -Encoding UTF8

# Register
$r1 = curl.exe -s -X POST "http://localhost:8080/api/app-user/register" -H "Content-Type: application/json" -d "@$bodyDir\register.json"
Write-Host "Register: $r1"

# Login
$r2 = curl.exe -s -X POST "http://localhost:8080/api/app-user/login" -H "Content-Type: application/json" -d "@$bodyDir\login.json"
Write-Host "Login: $r2"

# Extract token
if ($r2 -match '"token":"([^"]+)"') {
    $token = $Matches[1]
    Write-Host "Token: $token"

    # Add history body
    @{"phoneId"=294} | ConvertTo-Json -Compress | Out-File -FilePath "$bodyDir\history.json" -Encoding UTF8

    # Add history
    $r3 = curl.exe -s -X POST "http://localhost:8080/api/app-user/history/add" -H "Content-Type: application/json" -H "Authorization: Bearer $token" -d "@$bodyDir\history.json"
    Write-Host "AddHistory: $r3"

    # Get history
    $r4 = curl.exe -s -X GET "http://localhost:8080/api/app-user/history/list" -H "Authorization: Bearer $token"
    Write-Host "GetHistory: $r4"
}
