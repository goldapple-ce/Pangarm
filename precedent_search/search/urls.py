from django.urls import path, include
from search import views

urlpatterns = [
    path('', views.search),
    path('/summary', views.findSummaryByCaseNumber),
]
