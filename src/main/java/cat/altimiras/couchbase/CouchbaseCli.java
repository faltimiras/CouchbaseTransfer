package cat.altimiras.couchbase;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.RawJsonDocument;
import com.couchbase.client.java.error.DocumentAlreadyExistsException;

import java.util.List;

public class CouchbaseCli {

    private Cluster cluster;

    public CouchbaseCli(String host) {
        this.cluster = CouchbaseCluster.create(host);
    }

    public void insertDocuments(String bucketName, String psw, List<InsertDoc> docs) {
        Bucket bucket = cluster.openBucket(bucketName, psw);
        insert(bucket, docs);
    }

    public void insertDocuments(String bucketName, List<InsertDoc> docs) {
        Bucket bucket = cluster.openBucket(bucketName);
        insert(bucket, docs);
    }

    public void insertDocument(String bucketName, InsertDoc doc){
        Bucket bucket = cluster.openBucket(bucketName);
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
