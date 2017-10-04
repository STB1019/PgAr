"""Generate a random distribution of points, and extract the Convex Hull using
Graham Scan algorithm"""
import sys
import math
import random as rnd
from timeit import default_timer
import matplotlib.pyplot as plt

# Note that x_min and y_min are used to generate some random points
# but they can't be used here!
# Optimization: when searching for the begin point,
# if y are increasing then reverse the loop from that point (same for x)
def find_pivot(pts):
    """Find the beginning point for Gram Scan algorithm.
        For instance the beginning point is the closest
        to the origin (0,0) of the axes"""
    min_y = min_x = sys.maxint
    y_min_pts = []

    # Dumb solution, visit all the points
    # and evaluate min_y and min_x
    start = default_timer()
    for p_index in range(0, len(pts)-1):  # O(n)
        p_y = (pts[p_index])[1]
        if p_y <= min_y:
            min_y = p_y
            y_min_pts.append(p_index)

    # list filtering for min_y
    y_min_pts = [pt for pt in y_min_pts if (pts[pt])[1] == min_y]

    for pt_index in y_min_pts:
        pt_x = (pts[pt_index])[0]
        if pt_x < min_x:
            min_x = pt_x
            min_x_index = pt_index
    end = default_timer()
    print 'Method find_pivot execution time: %.8f seconds' % (end - start)
    return min_x_index


def angle_pivot(pivot, end_pt):
    """Evaluates the angle formed between the line passing through the given points
    and the x axis (y=0)"""
    line_slope = sys.maxint  # infinity, if the point are vertically aligned
    if end_pt[0] != pivot[0]:
        line_slope = (float(end_pt[1]) - float(pivot[1])) / \
            (float(end_pt[0]) - float(pivot[0]))
    elif end_pt[0] == pivot[0] and end_pt[1] == pivot[1]:
        line_slope = - sys.maxint  # -Infinity
    angle = math.atan(line_slope)
    return angle if angle >= 0 else angle + math.pi


def sort_angles_from_pivot(pts, pivot):
    """Sort the set of points by the angle they form with the pivot point"""
    start = default_timer()
    pts.remove(pivot) #O(n), any alternative solution?
    pts = sorted(pts, key=lambda p: angle_pivot(pivot, p))
    end = default_timer()
    print 'Method sort_angles_from_pivot execution time: %.8f seconds' % (end - start)
    return pts

def check_ccw(p1, p2, p3):
    """Three points are a counter-clockwise turn if ccw > 0, clockwise if 
    ccw < 0, and collinear if ccw = 0 because ccw is a determinant that
    gives twice the signed  area of the triangle formed by p1, p2 and p3."""
    return (p2[0]-p1[0])*(p3[1]-p1[1])-(p2[1]-p1[1])*(p3[0]-p1[0])

def gram_scan(pts, pivot):
    """Find the convex hull given the set of points with integer coordinates"""
    hull = [pivot] #hull initialization
    if len(pts) != 0:
        hull.append(pts.pop(0))

    while len(pts) > 0:
        pt = pts.pop(0)
        hull.append(pt)
        sz = len(hull)
        if check_ccw(hull[sz-3], hull[sz-2], hull[sz-1]) < 0:
            pts.insert(0, hull[-1])
            hull = hull[-2:]
    return hull


if __name__ == '__main__':
    # The random points can only have non-negative coordinates.
    # This simplifies a lot the the evaluation of the angle later
    x_min = y_min = -100 #-1000000000
    x_max = y_max = 100  # 1000000000
    instance_dimension = 60  # 300000
    points, convex_hull = [], []

    for index in range(instance_dimension):
        points.append((rnd.randint(x_min, x_max), (rnd.randint(y_min, y_max))))

    points = list(set(points)) #delete duplicates
    pivot_index = find_pivot(points)
    pivot = points[pivot_index]
    sorted_points = sort_angles_from_pivot(points, pivot)
    convex_hull = gram_scan(sorted_points, pivot)
    print 'Begin point index [pts] = ' + str(pivot_index) + ' at coordinates: [' + str(pivot[0]) + ',' + str(pivot[1]) + ']'

    counter = 0
    plt.plot(pivot[0], pivot[1], 'bs')
    plt.annotate(str(counter), xy=(pivot[0], pivot[1]))

    # for p in sorted_points:
    #     counter += 1
    #     plt.plot(p[0], p[1], 'ro')
    #     plt.plot([pivot[0], p[0]], [pivot[1], p[1]], 'k-')
    #     plt.annotate(str(counter), xy=(p[0], p[1]))

    for i in range(1, len(convex_hull)-1):
        current = convex_hull[i]
        previous = convex_hull[i-1]
        plt.plot(current[0], current[1], 'ro')
        plt.plot([previous[0], current[0]], [previous[1], current[1]], 'k-')

    plt.plot([x_min-100, x_max+100], [0, 0], 'r-') #x axis
    plt.plot([0, 0], [y_min-100, y_max+100], 'r-') #y axis
    plt.grid(True)
    plt.show()
