/*
 * Copyright (C) 2018 Seoul National University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.snu.nemo.conf;

import org.apache.reef.tang.annotations.Name;
import org.apache.reef.tang.annotations.NamedParameter;
import org.apache.reef.tang.formats.ConfigurationModule;
import org.apache.reef.tang.formats.ConfigurationModuleBuilder;
import org.apache.reef.tang.formats.OptionalParameter;
import org.apache.reef.tang.formats.RequiredParameter;

/**
 * Job Configurations.
 */
public final class JobConf extends ConfigurationModuleBuilder {

  //////////////////////////////// User Configurations

  /**
   * Job id.
   */
  @NamedParameter(doc = "Job id", short_name = "job_id")
  public final class JobId implements Name<String> {
  }

  /**
   * User Main Class Name.
   */
  @NamedParameter(doc = "User Main Class Name", short_name = "user_main")
  public final class UserMainClass implements Name<String> {
  }

  /**
   * User Main Arguments.
   */
  @NamedParameter(doc = "User Main Arguments", short_name = "user_args")
  public final class UserMainArguments implements Name<String> {
  }

  /**
   * Directory to store JSON representation of intermediate DAGs.
   */
  @NamedParameter(doc = "Directory to store intermediate DAGs", short_name = "dag_dir", default_value = "../dag")
  public final class DAGDirectory implements Name<String> {
  }

  /**
   * Directory to store files for storing blocks.
   */
  @NamedParameter(doc = "Directory to store files", short_name = "file_dir", default_value = "./files")
  public final class FileDirectory implements Name<String> {
  }

  /**
   * Directory points the mounted GlusterFS volume to store files in remote fashion.
   * If the volume is not mounted to this directory, the remote file store will act like local file store
   * (but maybe inefficiently).
   */
  @NamedParameter(doc = "Directory points the GlusterFS volume", short_name = "gfs_dir", default_value = "../tmp_gfs")
  public final class GlusterVolumeDirectory implements Name<String> {
  }

  //////////////////////////////// Compiler Configurations

  /**
   * The name of the optimization policy.
   */
  @NamedParameter(doc = "The canonical name of the optimization policy", short_name = "optimization_policy",
      default_value = "edu.snu.nemo.compiler.optimizer.policy.DefaultPolicy")
  public final class OptimizationPolicy implements Name<String> {
  }

  //////////////////////////////// Runtime Configurations

  /**
   * Deploy mode.
   */
  @NamedParameter(doc = "Deploy mode", short_name = "deploy_mode", default_value = "local")
  public final class DeployMode implements Name<String> {
  }

  /**
   * Nemo driver memory.
   */
  @NamedParameter(doc = "Nemo driver memory", short_name = "driver_mem_mb", default_value = "1024")
  public final class DriverMemMb implements Name<Integer> {
  }

  /**
   * Path to the JSON file that specifies resource layout.
   */
  @NamedParameter(doc = "Path to the JSON file that specifies resources for executors", short_name = "executor_json",
  default_value = "examples/resources/sample_executor_resources.json")
  public final class ExecutorJsonPath implements Name<String> {
  }

  /**
   * The fraction of container memory not to use fo the JVM heap.
   */
  @NamedParameter(doc = "The fraction of the container memory not to use for the JVM heap", short_name = "heap_slack",
      default_value = "0.3")
  public final class JVMHeapSlack implements Name<Double> {
  }

  /**
   * Contents of the JSON file that specifies resource layout.
   */
  @NamedParameter(doc = "Contents of JSON file that specifies resources for executors")
  public final class ExecutorJsonContents implements Name<String> {
  }

  /**
   * Executor capacity.
   * Determines the number of TaskGroup 'slots' for each executor.
   * 1) Master's TaskGroup scheduler can use this number in scheduling.
   *    (e.g., schedule TaskGroup to the executor currently with the maximum number of available slots)
   * 2) Executor's number of TaskGroup execution threads is set to this number.
   */
  @NamedParameter(doc = "Executor capacity", short_name = "executor_capacity", default_value = "1")
  public final class ExecutorCapacity implements Name<Integer> {
  }

  /**
   * Number of I/O threads for block fetch requests from other executor.
   */
  @NamedParameter(doc = "Number of I/O threads for block fetch request.", short_name = "io_request_threads",
      default_value = "5")
  public final class IORequestHandleThreadsTotal implements Name<Integer> {
  }

  /**
   * Scheduler timeout in ms.
   */
  @NamedParameter(doc = "Scheduler timeout in ms", short_name = "scheduler_timeout_ms", default_value = "10000")
  public final class SchedulerTimeoutMs implements Name<Integer> {
  }

  /**
   * Max number of attempts for task group scheduling.
   */
  @NamedParameter(doc = "Max number of schedules", short_name = "max_schedule_attempt", default_value = "3")
  public final class MaxScheduleAttempt implements Name<Integer> {
  }

  /**
   * The number of serialization threads for scheduling.
   */
  @NamedParameter(doc = "Number of serialization thread for scheduling", short_name = "schedule_ser_thread",
      default_value = "8")
  public final class ScheduleSerThread implements Name<Integer> {
  }

  /**
   * Hash range multiplier.
   * If we need to split or recombine an output data from a task after it is stored,
   * we multiply the hash range with this factor in advance
   * to prevent the extra deserialize - rehash - serialize process.
   * In these cases, the hash range will be (hash range multiplier X destination task parallelism).
   * The reason why we do not divide the output into a fixed number is that the fixed number can be smaller than
   * the destination task parallelism.
   */
  @NamedParameter(doc = "Hash range multiplier", short_name = "hash_range_multiplier", default_value = "10")
  public final class HashRangeMultiplier implements Name<Integer> {
  }

  /**
   * The TCP port to which local block transfer binds. 0 means random port.
   */
  @NamedParameter(doc = "Port to which PartitionTransport binds (0 means random port)",
      short_name = "block_port", default_value = "0")
  public final class PartitionTransportServerPort implements Name<Integer> {
  }

  /**
   * The maximum length which the pending connection queue of block transfer may grow to.
   */
  @NamedParameter(doc = "The maximum number of pending connections to PartitionTransport server",
      short_name = "block_backlog", default_value = "128")
  public final class PartitionTransportServerBacklog implements Name<Integer> {
  }

  /**
   * The number of listening threads of block transfer server.
   */
  @NamedParameter(doc = "The number of listening threads of PartitionTransport server",
      short_name = "block_threads_listening", default_value = "3")
  public final class PartitionTransportServerNumListeningThreads implements Name<Integer> {
  }

  /**
   * The number of block transfer server threads
   * which work on accepted connections.
   */
  @NamedParameter(doc = "The number of working threads of PartitionTransport server",
      short_name = "block_threads_working", default_value = "10")
  public final class PartitionTransportServerNumWorkingThreads implements Name<Integer> {
  }

  /**
   * The number of threads of block transfer client.
   */
  @NamedParameter(doc = "The number of threads of PartitionTransport client",
      short_name = "block_threads_client", default_value = "10")
  public final class PartitionTransportClientNumThreads implements Name<Integer> {
  }

  //////////////////////////////// Intermediate Configurations

  /**
   * Executor id.
   */
  @NamedParameter(doc = "Executor id")
  public final class ExecutorId implements Name<String> {
  }

  /**
   * Serialized {edu.snu.nemo.common.dag.DAG} from user main method.
   */
  @NamedParameter(doc = "String serialized DAG")
  public final class SerializedDAG implements Name<String> {
  }

  public static final RequiredParameter<String> EXECUTOR_ID = new RequiredParameter<>();
  public static final OptionalParameter<Integer> EXECUTOR_CAPACITY = new OptionalParameter<>();
  public static final RequiredParameter<String> JOB_ID = new RequiredParameter<>();
  public static final OptionalParameter<String> LOCAL_DISK_DIRECTORY = new OptionalParameter<>();
  public static final OptionalParameter<String> GLUSTER_DISK_DIRECTORY = new OptionalParameter<>();

  public static final ConfigurationModule EXECUTOR_CONF = new JobConf()
      .bindNamedParameter(ExecutorId.class, EXECUTOR_ID)
      .bindNamedParameter(ExecutorCapacity.class, EXECUTOR_CAPACITY)
      .bindNamedParameter(JobId.class, JOB_ID)
      .bindNamedParameter(FileDirectory.class, LOCAL_DISK_DIRECTORY)
      .bindNamedParameter(GlusterVolumeDirectory.class, GLUSTER_DISK_DIRECTORY)
      .build();
}
