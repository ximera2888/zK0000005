data class Post(
    val id: Int,
    val likes: Int,
    val owner_id: Int = 1,
    val fromId: Int = 2,
    val created_by: Int = 2,
    val date: Int = 2,
    val text: String = "Hallo, kotlin",
    val friends_only: Boolean ?=false,
    val can_delete: Boolean?=true
) {}

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
    var post = Post(1, 0)

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
    post = Post(2, 5)
    println("обновление поста= " + wallService.updatePost(post))

}