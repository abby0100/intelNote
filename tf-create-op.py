
import tensorflow as tf

#################################################
# create oprations
#################################################

# create constant op 1*2
matrix1 = tf.constant([[3., 3.]])
# create another constant op 2*1
matrix2 = tf.constant([[2.], [2.]])

# create a matrix multiplication for matrix1,2
product1 = tf.matmul(matrix1, matrix2)

#################################################
# create and start session
#################################################

#session = tf.Session()
#result = session.run(product)
#print result
#session.close()

# use with (file, thread, tf)
#with tf.Session() as session:
#	result = session.run(product1)
#	print result

session = tf.InteractiveSession()

x = tf.Variable([1.0, 2.0])
a = tf.constant([3.0, 3.0])

x.initializer.run()

sub = tf.sub(x, a)
print sub.eval()

