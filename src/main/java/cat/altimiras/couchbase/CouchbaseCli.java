package cat.altimiras.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CouchbaseCli {
	
	public Long TIMEOUT_DEFAULT = 10l;

    private Cluster cluster;
	private Long timeout;

    public CouchbaseCli(String host, Long timeout) {
        this.cluster = CouchbaseCluster.create(host);
		this.timeout = timeout == null ? TIMEOUT_DEFAULT : timeout;
    }

    public void insertDocuments(String bucketName, String psw, List<InsertDoc> docs) {
        Bucket bucket = cluster.openBucket(bucketName, psw, this.timeout, TimeUnit.SECONDS);
        insert(bucket, docs);
    }

    public void insertDocuments(String bucketName, List<InsertDoc> docs) {
        Bucket bucket = cluster.openBucket(bucketName, this.timeout, TimeUnit.SECONDS);
        insert(bucket, docs);
    }

    public void insertDocument(String bucketName, InsertDoc doc){
        Bucket bucket = cluster.openBucket(bucketName, this.timeout, TimeUnit.SECONDS);
        insert(bucket, doc);
    }

    private void insert(Bucket bucket, List<InsertDoc> docs){
        for (InsertDoc doc : docs) {
            insert(bucket, doc);
        }
    }

    private void insert(Bucket bucket, InsertDoc doc){
        if (doc.isFailIfPresent()) {
            try {
                bucket.insert(RawJsonDocument.create(doc.getKey(), doc.getContent()));
            } catch (DocumentAlreadyExistsException e) {
                System.out.println(String.format("ERROR: Can not insert document with key=%s because it is already stored", doc.getKey()));
            }
        } else {
            bucket.upsert(RawJsonDocument.create(doc.getKey(), doc.getContent()));
            System.out.println(String.format("Document with key=%s inserted", doc.getKey()));
        }

    }
}
