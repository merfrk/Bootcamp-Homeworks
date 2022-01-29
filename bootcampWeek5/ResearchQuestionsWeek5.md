## 1. Concurrent programlama ve Parallel Programlama nedir ? Aralarında çalışma şekli olarak nasıl bir fark bulunmaktadır ?

  Concurrent programlama örtüşüyormuş gibi görünen operasyonlar ve öncelikle belirleyici olmayan kontrol akışı nedeniyle ortaya çıkan karmaşıklıkla ilgilidir. Eşzamanlı programlarla ilişkili nicel maliyetler tipik olarak hem verim hem de gecikmedir.
  
  Paralel programlama, iş hacmini iyileştirme hedefi için çakışan işlemler ile ilgilidir. Eşzamanlı programlamanın zorlukları, kontrol akışını deterministik hale getirerek önlenir. Genellikle, programlar paralel çalışan alt görev kümeleri oluşturur ve üst görev yalnızca her alt görev tamamlandığında devam eder.

## 2. Mutex ve Semaphore kavramlarını açıklayınız. Hangi tür durumlarda bunlara başvurmamız gerekir ?

  Mutex, Bir bilgisayar uygulaması başlatıldığında, bir muteks oluşturacak ve onu bir kaynağa ekleyecektir. Kaynak bir iş parçacığı tarafından kullanıldığında kilitlenir ve diğer iş parçacıkları onu kullanamaz. Başka bir iş parçacığı aynı kaynağı kullanmak isterse, bir istekte bulunmalıdır. Daha sonra bu iş parçacığı, kaynakla ilk iş parçacığı bitene kadar bir kuyruğa yerleştirilecektir. Kaynakla ilk iş parçacığı bittiğinde, kilit kaldırılır ve kuyrukta bekleyen iş parçacığı kaynağa erişebilir. Kuyrukta bekleyen birden fazla iş parçacığı varsa, bunlara dönüşümlü olarak erişim verilir. Pratik olarak, muteks bir kaynağa erişimi birkaç iş parçacığı arasında değiştirdiğinde, birden çok iş parçacığı aynı anda bir kaynak tüketirken görünür olacaktır. Ancak dahili olarak belirli bir zamanda kaynağa yalnızca tek bir iş parçacığı erişiyor.

  Semafor, kritik bölümlere karşılıklı dışlama sağlamak için kullanılan bir veri yapısıdır. Semaforlar esas olarak bekleme (tarihsel olarak P olarak bilinir) ve sinyal (tarihsel olarak V olarak bilinir) olarak adlandırılan iki işlemi destekler. Bekleme işlemi, semafor açılana ve sinyal işlemi başka bir işlemin (iş parçacığı) girmesine izin verene kadar bir işlemi engeller. Her semafor, bir bekleme süreci kuyruğu ile ilişkilendirilir. Bekleme işlemi bir evre tarafından çağrıldığında, semafor açıksa evre devam edebilir. Bekleme işlemi bir iş parçacığı tarafından çağrıldığında semafor kapalıysa, iş parçacığı engellenir ve kuyrukta beklemesi gerekir. Sinyal işlemi bir semafor açar ve kuyrukta zaten bekleyen bir iş parçacığı varsa, bu işlemin devam etmesine izin verilir ve kuyrukta bekleyen iş parçacığı yoksa sinyal sonraki evreler için hatırlanır. Muteks semaforları ve sayma semaforları olarak adlandırılan iki tür semafor vardır. Mutex semaforları, bir kaynağa tek bir erişime izin verir ve sayma semaforları, birden çok iş parçacığının bir kaynağa erişmesine izin verir.
    
   Paralel programlama ortamlarında karşılıklı dışlamayı sağlamak için hem semaforlar hem de muteks nesneler kullanılsa da, bazı farklılıkları vardır. Muteks nesnesi yalnızca tek bir iş parçacığının bir kaynağı veya kritik bir bölümü tüketmesine izin verirken, semaforlar bir kaynağa sınırlı sayıda eşzamanlı erişime izin verir (izin verilen maksimum sayının altında). Mutex nesneleriyle, kaynağa erişmek isteyen diğer evreler, kaynağı kullanarak geçerli iş parçacığı bitene kadar bir kuyrukta beklemek zorundadır.
   
## 3. Java’da Error ve Exception arasındaki fark nedir ? Throwable ile ilişkileri nasıldır ? Hangi tür durumlarda Error hangi tür durumlarda Exception meydana gelebilir ? Örneklerler açıklayınız.
  
  İkisi de Throwabledan gelir. Error sistemsel durumlardan kaynaklanır. Exceptionları manipüle edebiliriz ama errorları manipüle edemeyiz. Sonsuz döngüye girilirse stackoverflow error alırız, olmayan bir indexe ulaşmaya çalışırsak indexOutOfBounds exception alırız.
  
## 4. Spring’te yer alan @Scheduled anotasyonunun kullanım amaçlarını ve kullanım şeklini açıklayınız.

  Timer sınıfının görevi verilen zamanda yürütmesi üzere zamanlamak için kullanılır. Verilen zaman geçmişteyse, görev yürütme için o harekette planlanır. @Scheduled anotasyonunu kullanmamız için metodumuzun void olması lazım ya da return edilen değer görmezden gelinir. Bir de metodumuzun parametre almaması lazım. Önce classa aşağıdaki anotasyonları ekleriz.
  
  ```
  @Configuration
@EnableScheduling
public class SpringConfig {
    ...
}
  ```

  Sonra aşağıdaki gibi metodumuza anotasyon ekleyerek istediimiz süreyi koyarız.
  
  ```
  @Scheduled(fixedDelay = 1000)
public void scheduleFixedDelayTask() {
    System.out.println(
      "Fixed delay task - " + System.currentTimeMillis() / 1000);
}
```
  
## 5. Spring’te yer alan @Async anotasyonunun kullanım amaçlarını ve kullanım şeklini açıklayınız.

  Metodun başka bir thread içinde çalıştırılması için kullanılır. Bu anotasyonu kullanabilmek için main methodda @EnableAsync anotasyonunun tanımlı olması gerekir.
  Kullanımı ise şu şekildedir:
  
  ```
  @Async
public Future<String> asyncMethodWithReturnType() {
    System.out.println("Execute method asynchronously - " 
      + Thread.currentThread().getName());
    try {
        Thread.sleep(5000);
        return new AsyncResult<String>("hello world !!!!");
    } catch (InterruptedException e) {
        //
    }

    return null;
}
```

## 6. High Availability (HA) kavramını kısa açıklayınız.

  High Availability bir sistemin belirli bir süre boyunca arızalanmadan sürekli çalışma yeteneğidir. HA, bir sistemin üzerinde anlaşmaya varılan bir operasyonel performans seviyesini karşılamasını sağlamak için çalışır.
  
## 7. Entity ve Value Object kavramlarını Domain Driven Design (DDD) kapsamında açıklayınız.
  
  DDD’da önemli bir kavram olan Entity, kendini diğer nesnelere nazaran tekilleştirebilmek için bir kimliğe(Id) sahip olan nesnelerdir. Entity, özünde Entity Framework’den aşina olunduğu gibi yeryüzündeki herhangi bir şey için modellenmiş nesnelere karşılık gelmektedir. Bahsedilen kimlik ise bu nesnelerin her biri için yaratıldığı süreçten itibaren diğerlerinden ayırmamızı sağlayan ve değişmeden taşınan Id değeridir.
  DDD prensibini kullanan veya kullanmayan çoğu projede value object’ler farkında olunsun ya da olunmasın kullanılan temel DDD yapı taşlarıdır diyebiliriz.

  Value object, herhangi bir kimlik(Id) değeri olmayan ve böylece aynı değerlere sahip iki value object nesnesinin değersel açıdan aynı olarak kabul edilebilir olmasını sağlayan ve dolayısıyla birbirlerinin yerine geçebilecekleri anlamına gelen bir nesnedir. İşte bu nedenle value object’ler her daim değişmez(immutable)dirler
  
## 8. Ubiquitous Language kavramını DDD kapsamında açıklayınız. Sizce neden önemli olabileceğini belirtiniz.

  Yazılım ekibi ile domain expert’leri arasında ortak ve açık bir dilin kurulması için kullanılır. Ortak dilin açık olması sayesinde gereksinimler üzerindeki belirsizlikler giderilmiş olacaktır. Ortak bir dil belirlemek test süreçlerini kolaylaştıracağı gibi gereksinimlerin değişmesi durumunda yazılım takımının bu değişiklikleri kolay anlayabilmesini sağlar.
  
## 9. Core Domain, Supporting Domain, Generic Domain kavramlarını DDD kapsamında açıklayınız.
  
  Çekirdek alan, iş için o kadar kritik ve temeldir ki, size rekabet avantajı sağlar ve işin arkasındaki temel kavramdır. Bu, en deneyimli insanlarınızın karmaşık  teknik veya altyapı sorunlarıyla uğraşmak yerine üzerinde çalışmasını istediğiniz alandır.
  
  Bu tür parçalar ayrıca, doğrudan işletmenin yaptığıyla ilgili yardımcı veya daha doğrusu destekleyici işlevlerin yerine getirilmesine yardımcı oldukları için gereklidir. Bu durumlarda, yüksek kaliteli kod ve mükemmel tasarlanmış yapı gerekli değildir.
  
  
  Bir kurumsal uygulama yazarken veya yeniden yazarken, sistemin işini kolaylaştıran, ancak işin özü olmayan bölümleri olacaktır. Örneğin, çoğu işletmede müşterilere fatura göndermek olan bir faturalandırma kavramı vardır. Bu kritik bir iş konsepti olsa da, işin "temel"i değildir.
  
## 10. Anemic Domain Model ve Rich Domain Model kavramlarını kıyaslayarak açıklayınız.

  Anemik Domain Modeli, içinde mantığı olmayan bir modeldir. Etki alanı sınıfları, sınıfın istemcisinin, sınıfın nasıl başlatılacağı ve değiştirileceği üzerinde kontrole sahip olduğu, etki alanı mantığı olmayan bir grup genel ayarlayıcı ve alıcıya benzer. Bu modellerde müşteri, sınıfın amacını ve kullanımını yorumlamalıdır. Genellikle mantık, hizmetler, yardımcı veya yönetici ve etki alanı sınıfının adı gibi bir şey olarak adlandırılan diğer sınıflara itilir. Mantık başka bir sınıfta otururken, müşterinin model sınıfında gezinmesine veya kullanmasına yardımcı olacak hiçbir şey yoktur.
  - Encapsulation ihlal edilmiş oluyor.
  - Bakımı zor.
  - Business logic duplica oluyor.
  - Modeldeki varlıkların tutarlı bir durumda olduğundan emin olamıyoruz.
  - Düşük cohesion.
  - Geliştirme ve iş dünyası arasında boşluğa ve yanlış anlaşılmaya sebep oluyor.
  
  Anemik Domain Modellerinin ana dezavantajlarını ele almanın iyi bir yolu, Rich Domain Modeli uygulamaktır. Anemik Domain Modeli ile arasındaki temel fark domain logiciğimizin domain entitilerimizin, veri ve davranışımızın bir parçası olmasıdır. Bu mantık, varlığın nasıl başlatıldığını, doğrulandığını ve işletildiğini yönlendirir ve kontrol eder, böylece istemcinin tutarsız bir duruma sahip varlıklara sahip olmasını önler.
