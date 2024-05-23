# Turizm Acente Sistemi Projesi

Bu proje, N Katmanlı Mimari yapı ile oluşturulmuş olup, bir turizm acentesinin günlük operasyonlarını ve müşteri rezervasyon süreçlerini yönetmek için bir otel yönetim sistemi geliştirmeyi amaçlamaktadır.

## Proje Hedefleri

- Müşteri rezervasyon süreçlerini optimize etmek.
- Otel ve oda yönetimini kolaylaştırmak.
- Esnek fiyatlandırma ve detaylı rezervasyon işlemleri sunmak.

## Kullanılan Teknolojiler
- JAVA
- SWING GUI
- PostgreSQL

## Veritabanı

Proje, PostgreSQL veritabanı kullanarak geliştirilmiştir. Veritabanında aşağıdaki tablolar bulunmaktadır:

1. `hotels`: Otellerin bilgilerini içerir.
2. `rooms`: Otellere ait odaların bilgilerini içerir.
3. `reservations`: Müşteri rezervasyonlarını içerir.
4. `seasons`: Otellerin dönem bilgilerini içerir.

Her tablo, projenin gereksinimlerini karşılamak için gerekli alanlara sahiptir.

## Özellikler

- Acente çalışanları otel ve oda ekleyebilir.
- Rezervasyonlar eklenip güncellenebilir ve silinebilir.
- Rezervasyon yapılırken misafir sayısına göre fiyatlar otomatik olarak hesaplanır.
- Oteller için minimum 2 dönem eklenmiştir.
- Stok yönetimi basit olarak dönem bazında oda stoğu takip edilir.

## Kurulum

1. PostgreSQL veritabanınızı kurun.
2. Proje dosyalarını bilgisayarınıza indirin.
3. Proje klasöründe `sql` dosyasını PostgreSQL'e içe aktarın.
4. Projeyi çalıştırın.

## Kullanım

1. Acente çalışanları sisteme otel ve oda ekleyebilir.
2. Rezervasyonlar eklenip güncellenebilir ve silinebilir.
3. Stoğa göre müsait odalar aranabilir ve rezervasyon yapılabilir.

# Not
Rezervasyon güncelleme kısmını yetiştiremedim, geliştirmeye devam ediyorum.
Çözmem gereken bazı bugları programı kullandıkça farkettim, bu süreçte onları da iyileştirmeye devam ediyorum.

