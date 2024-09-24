data class Post(
    val id: Int,
    val likes: Int,
    val owner_id: Int = 1,
    val fromId: Int = 2,
    val created_by: Int = 2,
    val date: Int = 2,
    val text: String = "Hallo, kotlin",
    val attachments: Array  <Attachment> = emptyArray(),
    val friends_only: Boolean ?=false,
    val can_delete: Boolean?=true
) {}
//data class attachmentPost():Array
data class Photo(val id: Int, val album_id:Int=0, val owner_id:Int)
data class Video(val id: Int,val owner_id:Int, val duration:Int )
data class Audio(val id: Int, val owner_id:Int, val artist:String="Noname",val album_id:Int=0)
data class File(val id: Int,  val owner_id:Int, val title: String, val size:Int)
data class Url(val url: String, val title: String,val caption: String,)
 class AttachmentPhoto(override var id:Int, override val type:String="Photo", override val title:String, val photo:Photo ):Attachment{

}
class AttachmentVideo(override var id:Int, override val type:String="Video", override val title:String,val video:Video  ):Attachment{

}
class AttachmentAudio(override var id:Int, override val type:String="Audio", override val title:String,val audio:Audio ):Attachment{

}
class AttachmentFile(override var id:Int, override val type:String="File", override val title:String,val file:File ):Attachment{

}
class AttachmentUrl(override var id:Int, override val type:String="Url", override val title:String ,val url:Url):Attachment{

}
//class Photo(override var id: Int = 1, override override val type: String): AttachmentPhoto
object likes {
    fun addLikes(post: Post): Post {
        val likeAdd = post.copy(likes = post.likes + 1)
        return likeAdd
    }
}

object wallService {
    var posts = emptyArray<Post>()
    private var idLast: Int = 0
    fun addPost(post: Post): Post {
        idLast = idLast + 1
        posts += post.copy(id = idLast)
        return posts.last()
    }

    fun updatePost(postUp: Post): Boolean {
        var update: Boolean = false
        for ((index, post) in posts.withIndex()) {
            if (post.id == postUp.id) {
                posts[index] = postUp.copy()
                update = true
            }
        }
        return update
    }

    // fun postsCount(posts: Array<Post>):Int{
    fun postsCount(): Int {
        return posts.size
    }

    fun postsLastId(): Int {
        return posts.last().id
    }

}

fun main(args: Array<String>) {
    var post = Post(1, 0, attachments = emptyArray())

    println(post)
    post = likes.addLikes(post)
    println(post)

    //проверка addPost
    wallService.addPost(post)
    println("Количество= " + wallService.postsCount())
    println("Последний id " + wallService.postsLastId())
    wallService.addPost(post)
    println("Количество= " + wallService.postsCount())
    println("Последний id " + wallService.postsLastId())

    //проверка update
    post = Post(2, 5, attachments = emptyArray())
    println("обновление поста= " + wallService.updatePost(post))

}