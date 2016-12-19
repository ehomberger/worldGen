package util;

import java.util.Random;

public class Noise
{

	public Noise()
	{

	}

	// generate white noise
	public static float[][] generateWhiteNoise(int width, int height)
	{
		Random rand = new Random();
		float[][] noiseArray = new float[width][height];

		for( int i = 0; i < width; i++ )
			for( int j = 0; j < height; j++ )
				noiseArray[i][j] = (float)rand.nextDouble() % 1;

		return noiseArray;
	}

	// generate smooth noise
	public static float[][] generateSmoothNoise(float[][] baseNoise, int octave)
	{
		int width = baseNoise.length;
		int height = baseNoise[0].length;

		float[][] smoothNoise = new float[width][height];

		int samplePeriod = 1 << octave;
		float sampleFrequency = 1.0f / samplePeriod;

		for (int i = 0; i < width; i++)
		{
			int sample_i0 = ( i/samplePeriod ) * samplePeriod;
			int sample_i1 = ( sample_i0 + samplePeriod ) % width;
			float horizontalBlend = ( i - sample_i0 ) * sampleFrequency;

			for (int j = 0; j < height; j++)
			{
				int sample_j0 = (j / samplePeriod) * samplePeriod;
				int sample_j1 = (sample_j0 + samplePeriod) % height;
				float verticalBlend = (j - sample_j0) * sampleFrequency;
			

				float top = Interpolate(
					baseNoise[sample_i0][sample_j0],
					baseNoise[sample_i1][sample_j0],
					horizontalBlend);

				float bot = Interpolate(
					baseNoise[sample_i0][sample_j1],
					baseNoise[sample_i1][sample_j1],
					horizontalBlend);

				smoothNoise[i][j] = Interpolate(top, bot, verticalBlend);
			}
		}

		return smoothNoise;
	}

	// Generate Perlin noise
	public static float[][] generatePerlinNoise(float[][] baseNoise, int octaveCount)
	{
		int width = baseNoise.length;
		int height = baseNoise[0].length;

		float[][][] smoothNoise = new float[octaveCount][][];

		float persistance = 0.5f;

		// generate smooth noise layers
		for(int i = 0; i < octaveCount; i++)
			smoothNoise[i] = generateSmoothNoise(baseNoise, i);

		float[][] perlinNoise = new float[width][height];
		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;

		// will it blend?
		// blend all of the layers together. Peristence comes into play here
		for(int octave = octaveCount - 1; octave >= 0; octave--)
		{
			amplitude *= persistance;
			totalAmplitude += amplitude;

			for(int i = 0; i < width; i++)
				for(int j = 0; j < width; j++)
					perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
		}

		// normalize
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				perlinNoise[i][j] /= totalAmplitude;

		return perlinNoise;
	}

	// Simple linear interpolation
	private static float Interpolate(float x0, float x1, float alpha)
	{
		return x0 * (1 - alpha) + x1 * alpha;
	}
}