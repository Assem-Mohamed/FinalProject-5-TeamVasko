# deploy-all.ps1

$services = @(
    "api-gateway",
    "eureka-class",
    "common-dto",
    "board-task-service",
    "user-service",
    "comment-log-service",
    "notification-service",
    "search-service"
)

foreach ($service in $services) {
    Write-Host "Deploying $service..." -ForegroundColor Cyan

    Push-Location $service

    Write-Host "Running 'mvn clean install' in $service"
    mvn clean install

    if ($LASTEXITCODE -ne 0) {
        Write-Host "Maven build failed for $service, skipping Docker build and push." -ForegroundColor Red
        Pop-Location
        continue
    }

    Write-Host "Building Docker image for $service"
    docker build -t assem1908/$service .

    if ($LASTEXITCODE -ne 0) {
        Write-Host "Docker build failed for $service." -ForegroundColor Red
        Pop-Location
        continue
    }

    Write-Host "Pushing Docker image for $service"
    docker push assem1908/$service

    Pop-Location
    Write-Host "$service deployed successfully." -ForegroundColor Green
}

Write-Host "✅ All microservices built and pushed successfully." -ForegroundColor Green
