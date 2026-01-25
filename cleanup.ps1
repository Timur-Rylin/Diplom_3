Write-Host "=== ОЧИСТКА ПРОЕКТА DIPLOM_3 ===" -ForegroundColor Cyan
Write-Host

# 1. Остановить Allure сервер если запущен
Write-Host "1. Останавливаю Allure сервер..." -ForegroundColor Yellow
Get-Process -Name "java" -ErrorAction SilentlyContinue | Where-Object { $_.CommandLine -like "*allure*" } | Stop-Process -Force -ErrorAction SilentlyContinue

# 2. Очистить через Maven
Write-Host "2. Запускаю mvn clean..." -ForegroundColor Yellow
mvn clean

# 3. Удалить директорию .allure (сохраняет ~100MB+)
if (Test-Path .allure) {
    Write-Host "3. Удаляю .allure директорию..." -ForegroundColor Yellow
    $size = "{0:N2} MB" -f ((Get-ChildItem .allure -Recurse -File | Measure-Object -Property Length -Sum).Sum / 1MB)
    Remove-Item -Recurse -Force .allure
    Write-Host "   Удалено: .allure ($size)" -ForegroundColor Green
}

# 4. Удалить файлы IntelliJ если не используете
if (Test-Path .idea) {
    Write-Host "4. Удаляю .idea директорию..." -ForegroundColor Yellow
    Remove-Item -Recurse -Force .idea
    Write-Host "   Удалено: .idea" -ForegroundColor Green
}

# 5. Удалить лог файл
if (Test-Path test_log.txt) {
    Write-Host "5. Удаляю test_log.txt..." -ForegroundColor Yellow
    Remove-Item -Force test_log.txt
    Write-Host "   Удалено: test_log.txt" -ForegroundColor Green
}

# 6. Проверить структуру
Write-Host "n=== ФИНАЛЬНАЯ СТРУКТУРА ===" -ForegroundColor Cyan
tree /f /a

# 7. Размер проекта
Write-Host "n=== РАЗМЕР ПРОЕКТА ===" -ForegroundColor Cyan
$totalSize = (Get-ChildItem -Recurse -File | Measure-Object -Property Length -Sum).Sum
Write-Host "Общий размер: {0:N2} MB" -f ($totalSize / 1MB) -ForegroundColor Green

Write-Host "`n=== ОЧИСТКА ЗАВЕРШЕНА ===" -ForegroundColor Green
